package io.github.zxbetter.activiti6;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        exclude = SecurityAutoConfiguration.class,
        scanBasePackages = {"io.github.zxbetter.activiti6", "org.activiti.rest.editor"}
)
public class SpringBootExample {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootExample.class, args);
    }
}
