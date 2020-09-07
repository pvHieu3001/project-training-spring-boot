package com.smartosc.training.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/login/oauth2/code/")
    @CrossOrigin
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    @CrossOrigin
    public String logout() {
        return "logout";
    }
}
