package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.entities.Admin;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.exceptions.InvalidDataException;
import edu.uy.um.wtf.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminWebController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/all")
    public String getAll(Model model){
        List<Admin> admins = adminService.getAll();
        model.addAttribute("admins", admins);
        return "admins/list";
    }

    @GetMapping("/byId{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        try {
            Admin admin = adminService.findById(id).get();
            model.addAttribute("admin", admin);
            return "admins/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Id not found");
            return "error";
        }
    }

    @GetMapping("/byFirstName&LastName{firstName}{lastName}")
    public String findByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, Model model){
        try {
            Admin admin = (Admin) adminService.findByFirstNameAndLastName(firstName, lastName);
            model.addAttribute("admin", admin);
            return "admins/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "First name and last name not found");
            return "error";
        }
    }

    @GetMapping("/add")
    public String addAdmin(@RequestParam Long id, @RequestParam String firstName,
                           @RequestParam String lastName, @RequestParam Date birthdate,
                           @RequestParam String mail, @RequestParam String username, @RequestParam String password,
                           Model model){
        try {
            Admin admin = adminService. addAdmin(id, firstName, lastName, birthdate,mail, username, password);
            model.addAttribute("admin", admin);
            return "admins/detail";
        } catch (InvalidDataException e) {
            model.addAttribute("error", "Invalid data");
            return "error";
        }
    }

    @GetMapping("/movieAdmin")
    public String showMovieAdminPage(){
        return "movieAdmin";
    }

    @GetMapping("/adminAdmin")
    public String showAdminAdminPage(){
        return "adminAdmin";
    }

    @GetMapping("/snacksAdmin")
    public String showSanckAdminPage(){
        return "snacksAdmin";
    }

    @GetMapping("/filmshowAdmin")
    public String showFilmshowAdminPage(){
        return "filmshowAdmin";
    }

    @GetMapping("/mainAdmin")
    public String shoMainAdminPage() {
        return "mainAdmin";
    }



}
