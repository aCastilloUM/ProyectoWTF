package edu.uy.um.wtf.controllers.entitiesControllers;

import edu.uy.um.wtf.entities.Admin;
import edu.uy.um.wtf.entities.FilmShow;
import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.entities.Snack;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.exceptions.InvalidDataException;
import edu.uy.um.wtf.services.AdminService;
import edu.uy.um.wtf.services.FilmShowService;
import edu.uy.um.wtf.services.MovieService;
import edu.uy.um.wtf.services.SnackService;
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

    @Autowired
    private MovieService movieService;

    @Autowired
    private SnackService snackService;

    @Autowired
    private FilmShowService filmShowService;

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

    @PostMapping("/delete")
    public String deleteAdmin(@RequestParam Long id, Model model) {
        try {
            Optional<Admin> admin = adminService.findById(id);
            adminService.delete(admin.orElse(null));
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Id not found");
            return "error";
        }
        return "mainAdmin";
    }

    @GetMapping("/movieAdmin")
    public String getAllMovie(Model model) {
        List<Movie> movieList = movieService.getAll();
        model.addAttribute("movieList", movieList);
        return "movieAdmin"; // Nombre de la vista debe coincidir con el archivo HTML sin extensión
    }

    @GetMapping("/snacksAdmin")
    public String showSanckAdminPage(Model model){
        List<Snack> snackList = snackService.getAll();
        model.addAttribute("snackList", snackList);
        return "snacksAdmin";
    }

    @GetMapping("/filmshowAdmin")
    public String showFilmshowAdminPage(Model model) {
        List<FilmShow> filmShowList = filmShowService.getAll();
        model.addAttribute("filmShowList", filmShowList);
        return "filmshowAdmin";
    }

    @GetMapping("/mainAdmin")
    public String shoMainAdminPage() {
        return "mainAdmin";
    }
}
