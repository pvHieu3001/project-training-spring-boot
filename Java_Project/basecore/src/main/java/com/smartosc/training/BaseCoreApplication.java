package com.smartosc.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BaseCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseCoreApplication.class, args);
    }
}
