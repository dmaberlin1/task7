package com.dmadev.task7.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class AppConfig {
    private final Environment env;

    @Autowired
    public AppConfig(Environment env) {
        this.env = env;
    }

    // Конфигурация DataSource, который предоставляет подключение к базе данных
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("db.driver"));
        dataSource.setJdbcUrl(env.getProperty("db.url"));
        dataSource.setUser(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    // Конфигурация EntityManagerFactory для работы с JPA (Java Persistence API)
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() throws PropertyVetoException{
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        // HibernateExceptions, PersistenceExceptions... to DataAccessException
        managerFactoryBean.setDataSource(dataSource());
        managerFactoryBean.setPackagesToScan("com.dmadev.task7.model");
        managerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        return managerFactoryBean;
    }

    // Конфигурация менеджера транзакций для управления транзакциями JPA
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
