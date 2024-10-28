package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam Long id, @RequestParam String firstName, @RequestParam String lastName,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate, @RequestParam String mail, @RequestParam String username,
                           @RequestParam String password, Model model) {

        Optional<User> existingUser = userService.findByEmail(mail);
        if (existingUser.isPresent()) {
            model.addAttribute("error", "El usuario ya fue registrado");
            return "error";
        }

        User newUser = userService.addUser(id, firstName, lastName, birthDate, mail, username, password);
        model.addAttribute("user", newUser);
        return "logIn";
    }

    @GetMapping("/paymentMethod")
    public String showPaymentMethodPage() {
        return "paymentMethod";
    }
}