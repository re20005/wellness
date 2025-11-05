package com.wellness.crud.wellness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLogin() {
        return "login"; 
    }

    @PostMapping("/login")
    public String fakeLogin() {
        // Simula que el login siempre es exitoso
        return "redirect:/home"; 
    }

   /*  @GetMapping("/home")
    public String home() {
        return "home"; // home.html
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register"; // register.html
    }*/
}
