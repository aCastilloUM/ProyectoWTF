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

    @PostMapping("/logInPost")
    public String logIn(@RequestParam String username, @RequestParam String password, Model model) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("error", "Nombre de usuario o contraseña invalidas");
            return "redirect:/logIn";
        }

        Optional<Admin> admin = adminService.findByUsername(username);

        if (admin.isPresent() && admin.get().getPassword().equals(password)){
            model.addAttribute("admin", admin.get());
            return "redirect:/admin/mainAdmin";
        }

        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            model.addAttribute("user", user.get());
            return "redirect:/users/main";
        }

        model.addAttribute("error", "Nombre de usuario o contraseña invalidas");
        return "redirect:/logIn";
    }

}



