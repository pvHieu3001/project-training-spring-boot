package com.smartosc.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 30/07/2020 - 9:17 AM
 * @created_by Hieupv
 * @since 30/07/2020
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
