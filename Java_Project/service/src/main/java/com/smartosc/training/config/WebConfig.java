package com.smartosc.training.config;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 10:48 AM
 * @created_by Namtt
 * @since 02/07/2020
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


  @Bean(name = "localeResolver")
  public LocaleResolver getLocaleResolver() {
    CookieLocaleResolver resolver = new CookieLocaleResolver();
    resolver.setCookieDomain("myAppLocaleCookie");
//    60 minutes
    resolver.setCookieMaxAge(60 * 60);
    return resolver;

  }

  @Bean(name = "messageSource")
  public MessageSource getMessageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    //Read file i18n/messages_XXX.properties
    messageSource.setBasename("classpath:i18n/messages");
    messageSource.setAlwaysUseMessageFormat(false);
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Bean
  public ModelMapper modelMapper(){
    return new ModelMapper();
  }


}
