package com.dmadev.task7.config;


import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;


public class AppInit implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Создаем контекст приложения и регистрируем конфигурацию
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);

        // Регистрируем контекст приложения в контексте сервлетов
        servletContext.addListener(new ContextLoaderListener(context));

        // Настраиваем кодировку для фильтра
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter("UTF-8", true, true));
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");

        // Настраиваем фильтр для скрытых HTTP-методов
        FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
        hiddenHttpMethodFilter.addMappingForUrlPatterns(null, false, "/*");

        // Регистрируем диспетчер сервлетов
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

}
