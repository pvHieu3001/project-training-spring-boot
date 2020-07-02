package com.smartosc.training.controllers;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 3:13 PM
 * @created_by Namtt
 * @since 02/07/2020
 */
@RestController
public class TestI18NController {
  @Autowired
  private MessageSource messageSource;

  @GetMapping("/")
  public String index(Locale locale){
    return messageSource.getMessage("hello",null,locale);
  }
}
