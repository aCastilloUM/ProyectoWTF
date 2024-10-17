package edu.uy.um.wtf.controllers;


import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import edu.uy.um.wtf.entities.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/users")
public class UserWebController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String getAll(Model model){
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/byId{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        try {
            User user = userService.findById(id).get();
            model.addAttribute("user", user);
            return "users/detail";
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
                          @RequestParam String lastName, @RequestParam Date birthDate,
                          @RequestParam String mail, Model model){
        try {
            User user = userService.addUser(id, firstName, lastName, birthDate, mail);
            model.addAttribute("user", user);
            return "users/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Error adding user");
            return "error";
        }
    }


}
