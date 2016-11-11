package ru.innopolis.mvc.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.innopolis.common.service.LessonService;
import ru.innopolis.common.service.StudentService;

/**
 * Created by Кирилл on 11.11.2016.
 */
@Configuration
@ComponentScan("ru.innopolis.mvc")
public class WebServiceConfig {

    @Bean
    public MapperFacade getMapperFacade() {
        return new DefaultMapperFactory.Builder().build().getMapperFacade();
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
