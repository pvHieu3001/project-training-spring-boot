package com.smartosc.training;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 01/07/2020 - 10:54 AM
 * @created_by Hieupv
 * @since 01/07/2020
 */

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableCaching
public class ServiceApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
