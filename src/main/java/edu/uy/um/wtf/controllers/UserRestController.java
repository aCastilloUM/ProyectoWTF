package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.exceptions.InvalidDataException;
import edu.uy.um.wtf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam Date birthDate, @RequestParam String mail) throws InvalidDataException {
        userService.addUser(id,firstName,lastName,birthDate,mail);
        return ResponseEntity.ok(userService.findByEmail(mail));
    }
}
