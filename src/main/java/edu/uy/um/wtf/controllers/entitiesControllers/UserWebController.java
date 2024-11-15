package edu.uy.um.wtf.controllers.entitiesControllers;


import edu.uy.um.wtf.entities.Bill;
import edu.uy.um.wtf.entities.FilmShow;
import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.services.FilmShowService;
import edu.uy.um.wtf.services.MovieService;
import edu.uy.um.wtf.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private MovieService movieService;

    @Autowired
    private FilmShowService filmShowService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/registerPost")
    public String registerUser(@RequestParam Long id, @RequestParam String username, @RequestParam String password,
                               @RequestParam String firstName, @RequestParam String lastName,
                               @RequestParam String email, @RequestParam("birthDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate, Model model, HttpSession session) {
        if (id == null || username == null || username.isEmpty() || password == null || password.isEmpty() ||
                firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() ||
                email == null || email.isEmpty() || birthDate == null) {
            model.addAttribute("error", "Todos los campos son requeridos");
            return "redirect:/register";
        }

        User newUser = User.builder()
                .id(id)
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .mail(email)
                .birthDate(birthDate)
                .build();

        userService.saveUser(newUser);
        session.setAttribute("user", newUser);
        return "redirect:/logIn";
    }

    @GetMapping("/paymentMethod")
    public String showPaymentMethodPageGet() {
        return "paymentMethod";
    }

    @PostMapping("/PaymentMethodPost")
    public String showPaymentMethodPagePost(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/logIn";
        }

        Bill bill = (Bill) session.getAttribute("bill");
        if (bill == null) {
            return "redirect:/snacks";
        }

        model.addAttribute("bill", bill);
        return "redirect:/bills/paymentSuccess";
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
