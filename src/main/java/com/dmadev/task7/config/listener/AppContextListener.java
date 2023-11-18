package com.dmadev.task7.config.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class AppContextListener implements ServletContextListener {

    // Метод вызывается при инициализации контекста приложения
    @Override
    public void contextInitialized(ServletContextEvent sce){
        System.out.println("************* Starting up! ************");
    }


    // Метод вызывается при уничтожении контекста приложения
        public void contextDestroyed(ServletContextEvent sce){
            System.out.println("************** Shutting down! **************");
            System.out.println("Destroying Context...");
            System.out.println("Calling PostgreSQL AbandonedConnectionCleanupThread checkedShutdown");

            // ... First close any background tasks which may be using the DB ...
            // ... Then close any DB connection pools ...

            // Now deregister JDBC drivers in this context's ClassLoader:
            // Get the webapp's ClassLoader

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            //Loop through all drivers
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                // This driver was registered by the webapp's ClassLoader, so deregister it:
                if (driver.getClass().getClassLoader() == classLoader) {
                    try {
                        System.out.println("Deregistering JDBC driver " + driver);
                        DriverManager.deregisterDriver(driver);
                    } catch (SQLException sqlException){
                        System.out.println("Error deregistering JDBC driver" + driver);
                        sqlException.printStackTrace();
                    }
                } else {
                    // driver was not registered by the webapp's ClassLoader and may be in use elsewhere
                    System.out.println("Not deregistering JDBC driver " + driver + " as it does not belong to this webapp's ClassLoader");
                }
            }

        }

}

/*  Метод contextDestroyed выполняет ряд действий:

        Закрывает любые фоновые задачи, которые могут использовать базу данных.
        Закрывает любые пулы соединений с базой данных.
        Освобождает JDBC-драйверы, зарегистрированные в контексте текущего ClassLoader'а.
         Каждый JDBC-драйвер, зарегистрированный с использованием DriverManager, имеет свой ClassLoader.
         В случае веб-приложений, это может быть важным, чтобы избежать утечек памяти при перезапуске приложения
         или перезагрузке веб-контейнера.
 */
