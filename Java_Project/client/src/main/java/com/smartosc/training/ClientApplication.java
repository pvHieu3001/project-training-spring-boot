package com.smartosc.training;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 01/07/2020 - 10:56 AM
 * @created_by Hieupv
 * @since 01/07/2020
 */
@SpringBootApplication
@EnableEurekaServer
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
