package com.otus.hw_09.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/home";
    }

}
