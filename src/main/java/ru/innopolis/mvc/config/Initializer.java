package ru.innopolis.mvc.config;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletRegistration.Dynamic;

import javax.servlet.*;
import java.util.EnumSet;

import static ru.innopolis.mvc.config.Constance.DISPATCHER_SERVLET_NAME;

/**
 * Created by Кирилл on 03.11.2016.
 */
public class Initializer implements WebApplicationInitializer {

    // Указываем имя нашему Servlet Dispatcher для мапинга


    /**
     * Настройка контейнера сервлетов вместо web.xml
     *
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        // Регистрируем в контексте конфигурационный класс MVC, Security
        ctx.register(WebAppConfig.class);
        ctx.register(SecurityConfig.class);
        ctx.register(JPAConfig.class);

        servletContext.addListener(new ContextLoaderListener(ctx));

        ctx.setServletContext(servletContext);

        // Добавляем сервлет - диспатчер
        Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);


        //Фильтр Spring Security
        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");
        //Фильтр перекодировки в UTF-8
        servletContext.addFilter("charsetFilter", new CharacterEncodingFilter("UTF-8", true))
                .addMappingForUrlPatterns(null, false, "/*");

        //Фильтр OpenEntityManagerInViewFilter для Lazy загрузки полей
        servletContext.addFilter("oemInViewFilter", new OpenEntityManagerInViewFilter())
                .addMappingForUrlPatterns(null, false, "/*");


    }

}
