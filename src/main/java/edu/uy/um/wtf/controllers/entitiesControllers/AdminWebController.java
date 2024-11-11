package edu.uy.um.wtf.controllers.entitiesControllers;

import edu.uy.um.wtf.entities.Admin;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.exceptions.InvalidDataException;
import edu.uy.um.wtf.services.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminWebController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/adminAdmin")
    public String getAllAdmins(Model model) {
        List<Admin> adminList = adminService.getAll();
        model.addAttribute("adminList", adminList);
        return "adminAdmin"; // Nombre de la vista debe coincidir con el archivo HTML sin extensión
    }

    @GetMapping("/info/{id}")
    public String getAdminInfo(@PathVariable("id") Long id, Model model) {
        Optional<Admin> admin = adminService.getById(id); // Ajusta el método según tu servicio
        if (admin.isPresent()) {
            model.addAttribute("admin", admin.get());
            return "adminInfo"; // Envía a la vista de detalles del administrador
        } else {
            model.addAttribute("error", "Administrador no encontrado");
            return "error"; // En caso de que el administrador no sea encontrado
        }
    }

    //crear una findByUsername
    @GetMapping("/byUsername/{username}")
    public String findByUsername(@PathVariable("username") String username, Model model) {
        try {
            Optional<Admin> admin = adminService.findByUsername(username);
            if (admin.isPresent()) {
                model.addAttribute("admin", admin.get());
                return "adminInfo";
            } else {
                model.addAttribute("error", "Username not found");
                return "error";
            }
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Username not found");
            return "adminAdmin";
        }
    }

    @GetMapping("/byFirstName&LastName{firstName}{lastName}")
    public String findByFirstNameAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, Model model){
        try {
            Admin admin = (Admin) adminService.findByFirstNameAndLastName(firstName, lastName);
            model.addAttribute("admin", admin);
            return "adminDetail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "First name and last name not found");
            return "error";
        }
    }

    @PostMapping("/add")
    public String addAdmin(@RequestParam Long id, @RequestParam String firstName,
                           @RequestParam String lastName, @RequestParam("birthDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdate,
                           @RequestParam String mail, @RequestParam String username, @RequestParam String password,
                           Model model, HttpSession session, RedirectAttributes redirectAttributes){
        try {
            Admin admin = adminService. addAdmin(id, firstName, lastName, birthdate,mail, username, password);
            model.addAttribute("admin", admin);
            session.setAttribute("admin", admin);
            return "mainAdmin";
        } catch (InvalidDataException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid data");
            return "error";
        }
    }


    @GetMapping("/movieAdmin")
    public String showMovieAdminPage(){
        return "movieAdmin";
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
