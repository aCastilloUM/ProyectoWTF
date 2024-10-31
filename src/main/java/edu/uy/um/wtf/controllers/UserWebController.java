package edu.uy.um.wtf.controllers;


import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import edu.uy.um.wtf.entities.User;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/users")
public class UserWebController {

    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String showUserPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "main";
        }else {
            return "redirect:/logIn";
        }

    }


    @GetMapping("/all")
    public String getAll(Model model){
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/registerPost")
    public String registerUser(@RequestParam String username, @RequestParam String password,
                               @RequestParam String firstName, @RequestParam String lastName,
                               @RequestParam String email, @RequestParam Date birthDate, Model model) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() ||
                firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() ||
                email == null || email.isEmpty() || birthDate == null) {
            model.addAttribute("error", "Todos los campos son requeridos");
            return "redirect:/register";
        }

        User newUser = User.builder()
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .mail(email)
                .birthDate(birthDate)
                .build();

        userService.saveUser(newUser);
        return "redirect:/logIn";
    }

    @GetMapping("/paymentMethod")
    public String showPaymentMethodPage() {
        return "paymentMethod";
    }

    @GetMapping("/byId/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        try {
            User user = userService.findById(id).get();
            model.addAttribute("user", user);
            return "register";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Id not found");
            return "error";
        }
    }

    @GetMapping("/byEmail{email}")
    public String findByEmail(@PathVariable("email") String email, Model model){
        try {
            User user = userService.findByEmail(email).get();
            model.addAttribute("user", user);
            return "users/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Email not found");
            return "error";
        }
    }

    @GetMapping("/add")
    public String addUser(@RequestParam Long id, @RequestParam String firstName,
                          @RequestParam String lastName, @RequestParam("birthDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate,
                          @RequestParam String mail, @RequestParam String username, @RequestParam String password, Model model){
        try {
            User user = userService.addUser(id, firstName, lastName, birthDate, mail, username, password);
            model.addAttribute("user", user);
            return "users/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Error adding user");
            return "error";
        }
    }


}
