package com.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String start() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }


}
