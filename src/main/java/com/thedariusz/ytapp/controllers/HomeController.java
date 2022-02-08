package com.thedariusz.ytapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String displayHomePage() {
        return "home";
    }

    @GetMapping("/home/yt")
    public String displayLoginPage() {
        return "yt";
    }
}
