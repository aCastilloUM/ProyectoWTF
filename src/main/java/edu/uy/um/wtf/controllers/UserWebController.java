package edu.uy.um.wtf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserWebController {
    @GetMapping("/logIn")
    public String logIn(){ return "logIn";}
}
