package com.namami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Anand Jha
 */
@SpringBootApplication
@PropertySource("classpath:dbquery.properties")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
