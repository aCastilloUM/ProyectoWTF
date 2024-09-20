package edu.uy.um.wtf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // nombre del archivo HTML en src/main/resources/templates
    }

    @PostMapping("/login")
    public String login() {
        // lógica de autenticación
        return "redirect:/home"; // redirige a la página de inicio o dashboard
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // nombre del archivo HTML en src/main/resources/templates
    }

    @PostMapping("/register")
    public String register() {
        // lógica de registro
        return "redirect:/login"; // redirige a la página de inicio de sesión
    }
}
