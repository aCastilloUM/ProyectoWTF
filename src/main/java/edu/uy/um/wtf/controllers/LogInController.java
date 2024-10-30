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
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Nombre de usuario o contraseña inválidos");
        }
        return "logIn";
    }

    @PostMapping("/logIn")
    public String logIn(@RequestParam String userName, @RequestParam String password, Model model) {

        if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("error", "Nombre de usuario o contraseña invalidas");
            return "redirect:/logIn";
        }
        Optional<Admin> admin = adminService.findByUserName(userName);

        if (admin.isPresent() && admin.get().getPassword().equals(password)){
            model.addAttribute("admin", admin.get());
            return "redirect:/admin/mainAdmin";
        }
        Optional<User> user = userService.findByUserName(userName);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            model.addAttribute("user", user.get());
            return "redirect:/users/main";
        } else {
            model.addAttribute("error", "Nombre de usuario o contraseña invalidas");
            return "redirect:/logIn";
        }
    }

}