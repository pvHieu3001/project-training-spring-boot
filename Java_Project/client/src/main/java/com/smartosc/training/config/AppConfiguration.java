package com.smartosc.training.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Fresher-Training
 *
 * @author Hieupv
 * @created_at 30/07/2020 - 2:14 PM
 * @created_by Hieupv
 * @since 30/07/2020
 */
@Configuration
public class AppConfiguration implements WebMvcConfigurer {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        converter.setObjectMapper(objectMapper);
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(1000);
        httpRequestFactory.setConnectTimeout(1000);
        httpRequestFactory.setReadTimeout(1000);

        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.setRequestFactory(httpRequestFactory);
        return restTemplate;
    }
}
