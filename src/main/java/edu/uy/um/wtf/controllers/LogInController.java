package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.entities.Admin;
import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.services.AdminService;
import edu.uy.um.wtf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LogInController {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/logIn")
    public String showLoginForm() {
        return "logIn";
    }

    @PostMapping("/logIn")
    public String logIn(@RequestParam String userName, @RequestParam String password, Model model) {

        User user = userService.authenticate(userName, password);
        Boolean admin = adminService.authenticate(userName, password);

        if (admin.booleanValue()){
            model.addAttribute("admin", admin);
            return "mainAdmin";
        }
        if (user != null) {
            model.addAttribute("user", user);
            return "main";
        } else {
            model.addAttribute("error", "Nombre de usuario o contraseña invalidas");
            return "logIn";
        }

    }

}