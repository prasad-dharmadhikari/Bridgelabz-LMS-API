package com.bridgelabz.lmsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class LmsapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(LmsapiApplication.class, args);
    }
}
