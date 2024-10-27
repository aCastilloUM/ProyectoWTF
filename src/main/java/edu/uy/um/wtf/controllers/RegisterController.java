package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String id, @RequestParam String firstName, @RequestParam String lastName,
                           @RequestParam String birthDate, @RequestParam String mail, @RequestParam String username,
                           @RequestParam String password, Model model) {

        boolean isRegistered = userService.register(id, firstName, lastName, birthDate, mail, username, password);
        if (isRegistered) {
            return "redirect:/logIn";
        } else {
            model.addAttribute("error", "Registration failed. Please try again.");
            return "register";
        }
    }
}