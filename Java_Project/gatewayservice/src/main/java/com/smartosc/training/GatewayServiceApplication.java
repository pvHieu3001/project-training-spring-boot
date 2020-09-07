package com.smartosc.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 30/07/2020 - 11:15 AM
 * @created_by Hieupv
 * @since 30/07/2020
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients

public class GatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}
