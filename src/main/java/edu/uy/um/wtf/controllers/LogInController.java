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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String showLoginForm() {
        return "logIn";
    }

    @PostMapping("/logInPost")
    public String logIn(@RequestParam String username, @RequestParam String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Nombre de usuario o contraseña invalidas");
            return "redirect:/logIn";
        }

        Optional<Admin> admin = adminService.findByUsername(username);
        if (admin.isPresent() && password.equals(admin.get().getPassword())) {
            session.setAttribute("admin", admin.get());
            return "redirect:/admin/mainAdmin";
        }

        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent() && password.equals(user.get().getPassword())) {
            session.setAttribute("user", user.get());
            return "redirect:/users/main";
        }

        redirectAttributes.addFlashAttribute("error", "Nombre de usuario o contraseña invalidas");
        return "redirect:/logIn";
    }

    @GetMapping("/logOut")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/logIn";
    }
}



