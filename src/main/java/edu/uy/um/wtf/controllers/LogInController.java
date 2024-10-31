package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.entities.Admin;
import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.services.AdminService;
import edu.uy.um.wtf.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/logIn")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Nombre de usuario o contraseña inválidos");
        }
        return "logIn";
    }

    @PostMapping("/logIn")
    public String logIn(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("error", "Nombre de usuario o contraseña invalidas");
            return "redirect:/logIn";
        }
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser != null && sessionUser.getUsername().equals(username)) {
            return "redirect:/users/main";
        }

//        Optional<Admin> admin = adminService.findByUsername(username);
//
//        if (admin.isPresent() && passwordEncoder.matches(password, admin.get().getPassword())){
//            session.setAttribute("admin", admin.get());
//            model.addAttribute("admin", admin.get());
//            return "redirect:/admin/mainAdmin";
//        }
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            session.setAttribute("user", user.get());
            model.addAttribute("user", user.get());
            return "redirect:/users/main";
        } else {
            model.addAttribute("error", "Nombre de usuario o contraseña invalidas");
            return "redirect:/logIn";
        }
    }

}



