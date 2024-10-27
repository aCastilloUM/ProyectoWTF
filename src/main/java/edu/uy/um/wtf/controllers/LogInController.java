package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.services.AdminService;
import edu.uy.um.wtf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogInController {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String redirectToLogin() {
        return "logIn";
    }

    @GetMapping("/logIn")
    public String showLoginForm() {
        return "logIn";
    }

    @PostMapping("/logIn")
    public String logIn(@RequestParam String userName, @RequestParam String password, Model model) {
        boolean isUserAuthenticated = userService.authenticate(userName, password);
        if (isUserAuthenticated) {
            return "redirect:/users/home";
        }

        boolean isAdminAuthenticated = adminService.authenticate(userName, password);
        if (isAdminAuthenticated) {
            return "redirect:/admin/home";
        }

        model.addAttribute("error", "Nombre de usuario o contraseña incorrectos");
        return "logIn";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }
}