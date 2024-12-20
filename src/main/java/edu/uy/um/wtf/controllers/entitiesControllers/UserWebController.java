package edu.uy.um.wtf.controllers.entitiesControllers;


import edu.uy.um.wtf.entities.*;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.services.FilmShowService;
import edu.uy.um.wtf.services.MovieService;
import edu.uy.um.wtf.services.PaymentMethodService;
import edu.uy.um.wtf.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/users")
public class UserWebController {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private FilmShowService filmShowService;

    @GetMapping("/main")
    public String showUserPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Movie> movies = movieService.getAll();
            model.addAttribute("movies", movies);

            // Obtenemos la primera película
            if (!movies.isEmpty()) {
                Movie firstMovie = movies.get(0);
                model.addAttribute("firstMovie", firstMovie);
                model.addAttribute("firstMovieImagePath", firstMovie.getPosterPath());
            }

            // Obtenemos la segunda película
            if (movies.size() > 1) {
                Movie secondMovie = movies.get(1);
                model.addAttribute("secondMovie", secondMovie);
                model.addAttribute("secondMovieImagePath", secondMovie.getPosterPath());
            }

            // Obtenemos la tercera película
            if (movies.size() > 2) {
                Movie thirdMovie = movies.get(2);
                model.addAttribute("thirdMovie", thirdMovie);
                model.addAttribute("thirdMovieImagePath", thirdMovie.getPosterPath());
            }

            model.addAttribute("user", user);
            return "main";

        } else {
            return "redirect:/logIn";
        }
    }

    @GetMapping("/movies/{id}")
    public String getMovieDetails(@PathVariable Long id, Model model) {
        Movie movie = movieService.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        List<Movie> movies = movieService.getAll();
        List<FilmShow> filmShows = filmShowService.findByMovieId(id);
        model.addAttribute("movies", movies);
        model.addAttribute("selectedMovie", movie);
        model.addAttribute("filmShows", filmShows);
        return "main";
    }

    @GetMapping("/all")
    public String getAll(Model model){
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "userList";
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
