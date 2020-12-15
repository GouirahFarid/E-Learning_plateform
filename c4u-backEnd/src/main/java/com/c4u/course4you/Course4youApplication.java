package com.c4u.course4you;

import com.c4u.course4you.entities.CourseResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CourseResource.class)
public class Course4youApplication {
    public static void main(String[] args) {
        SpringApplication.run(Course4youApplication.class, args);
    }

}
