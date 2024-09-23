package edu.uy.um.wtf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // Esto debe coincidir con el nombre de tu plantilla HTML de registro
    }
}