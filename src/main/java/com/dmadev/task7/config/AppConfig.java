package com.dmadev.task7.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class AppConfig {

    private final Environment env;

    public AppConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(env.getProperty("db.driver"));
            dataSource.setJdbcUrl(env.getProperty("db.url"));
            dataSource.setUser(env.getProperty("db.username"));
            dataSource.setPassword(env.getProperty("db.password"));
        }catch (PropertyVetoException e){
            // Handle or rethrow as a more generic exception
            throw new RuntimeException("Error configuring data source", e);
        }

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        // HibernateExceptions, PersistenceExceptions... to DataAccessException
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.dmadev.task7.models");
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
