package ru.innopolis.mvc.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.innopolis.mvc.entity.Student;
import ru.innopolis.mvc.service.LessonService;
import ru.innopolis.mvc.service.StudentService;
import ru.innopolis.mvc.service.impl.UserDetailsServiceImpl;

import javax.sql.DataSource;

/**
 * Created by Кирилл on 03.11.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan("ru.innopolis.mvc")
public class WebAppConfig extends WebMvcConfigurerAdapter {


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
     * @return сервис
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public MapperFacade getMapperFacade() {
        return new DefaultMapperFactory.Builder().build().getMapperFacade();
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

    @Bean
    public RmiServiceExporter registerServiceUser(UserDetailsService userDetailsService) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("UserDetailsService");
        rmiServiceExporter.setService(userDetailsService);
        rmiServiceExporter.setServiceInterface(UserDetailsService.class);
        rmiServiceExporter.setRegistryPort(5000);
        return rmiServiceExporter;
    }

    @Bean
    public RmiServiceExporter registerServiceLesson(LessonService lessonService) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("LessonService");
        rmiServiceExporter.setService(lessonService);
        rmiServiceExporter.setServiceInterface(LessonService.class);
        rmiServiceExporter.setRegistryPort(5000);
        return rmiServiceExporter;
    }

    @Bean
    public RmiServiceExporter registerServiceStudent(StudentService studentService) {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("StudentService");
        rmiServiceExporter.setService(studentService);
        rmiServiceExporter.setServiceInterface(StudentService.class);
        rmiServiceExporter.setRegistryPort(5000);
        return rmiServiceExporter;
    }

}
