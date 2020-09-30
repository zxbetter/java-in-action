package io.github.zxbetter.activiti6;

import org.activiti.engine.ProcessEngine;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringBootExample {
    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootExample.class, args);


        applicationContext.getBean(ProcessEngine.class)
    }
}
