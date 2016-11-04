package ru.innopolis.mvc.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletRegistration.Dynamic;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by Кирилл on 03.11.2016.
 */
public class Initializer implements WebApplicationInitializer {

    // Указываем имя нашему Servlet Dispatcher для мапинга
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        // Регистрируем в контексте конфигурационный класс, который мы создадим ниже
        ctx.register(WebAppConfig.class);
        ctx.register(SecurityConfig.class);

        servletContext.addListener(new ContextLoaderListener(ctx));
        ctx.setServletContext(servletContext);


        Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");

        servletContext.addFilter("charsetFilter", new CharacterEncodingFilter("UTF-8",true))
                .addMappingForUrlPatterns(null, false, "/*");


    }

}
