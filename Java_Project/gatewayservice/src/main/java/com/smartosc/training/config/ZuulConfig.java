package com.smartosc.training.config;

import com.smartosc.training.filter.ErrorFilter;
import com.smartosc.training.filter.PostFilter;
import com.smartosc.training.filter.PreFilter;
import com.smartosc.training.filter.RouteFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 31/07/2020 - 10:17 AM
 * @created_by Hieupv
 * @since 31/07/2020
 */
@Configuration
public class ZuulConfig {
    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }
}
