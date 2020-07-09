/*
 * Copyright (C) 2020 Viettel Digital Services. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.smartosc.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;
import java.util.Date;

//@Configuration
//@EnableSwagger2
//@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
//public class SwaggerConfiguration {
//
//  @Autowired
//  private AppConfig appConfig;
//
//  @Bean
//  public Docket api() {
//    return new Docket(DocumentationType.SWAGGER_2).select()
//            .apis(RequestHandlerSelectors
//                    .basePackage("com.smartosc.training.controllers"))
//            .paths(PathSelectors.regex("/.*"))
//            .build().apiInfo(apiEndPointsInfo())
//            .directModelSubstitute(Timestamp.class, Date.class)
//            .useDefaultResponseMessages(false);
//
//  }
//
//
//  private ApiInfo apiEndPointsInfo() {
//    return new ApiInfoBuilder().title(appConfig.getApplicationShortName())
//            .description(appConfig.getApplicationConfiguration())
//            .version("1.0.0")
//            .build();
//  }
//
//}


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
  }
}