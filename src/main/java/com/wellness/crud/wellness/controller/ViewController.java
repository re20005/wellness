package com.wellness.crud.wellness.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.servlet.view.RedirectView; 


@Controller
public class ViewController {

  /*   @PostMapping("/login")
    public RedirectView doLogin(@RequestParam(required = false) String email,
                                @RequestParam(required = false) String password) {
        // Por ahora no validamos, solo redirigimos al home
        return new RedirectView("/home"); 
    }*/

    @GetMapping("/register")
    public String register() {
        return "register"; 
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }
}
