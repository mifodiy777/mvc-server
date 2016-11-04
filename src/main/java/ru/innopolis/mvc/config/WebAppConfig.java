package ru.innopolis.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.innopolis.mvc.service.impl.UserDetailsServiceImpl;

import javax.sql.DataSource;

/**
 * Created by Кирилл on 03.11.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan("ru.innopolis.mvc")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    private static final String URL_BASE = "jdbc:mysql://127.0.0.1:3306/students?useSSL=false";

    // Позволяет видеть все ресурсы в корне, такие как картинки, стили и т.п.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
    }

    /**
     * Кастомный сервис аутентификации
     *
     * @return
     */
    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsServiceImpl();
    }

    /**
     * Создаем DataSource - MySQL
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL_BASE, "root", "root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }

    // а этот бин инициализирует View нашего проекта
    // точно это же мы делали в mvc-dispatcher-servlet.xml
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

}
