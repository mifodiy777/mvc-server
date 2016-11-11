package ru.innopolis.mvc.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Created by Кирилл on 11.11.2016.
 */
public class StudentStart {

    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        // Регистрируем в контексте конфигурационный класс MVC, Security
        ctx.register(WebServiceConfig.class);
        ctx.register(JPAConfig.class);
        ctx.refresh();
    }

}

