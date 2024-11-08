package edu.uy.um.wtf.controllers.entitiesControllers;

import edu.uy.um.wtf.entities.FilmShow;
import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.exceptions.InvalidDataException;
import edu.uy.um.wtf.services.FilmShowService;
import edu.uy.um.wtf.services.MovieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieWebController {


    @Autowired
    private MovieService movieService;

    @Autowired
    private FilmShowService filmShowService;

    //Puedo agregar otra ruta para que acceda:
    @GetMapping("/list")
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.getAll();
        model.addAttribute("movies", movies);
        return "main";
    }

    @GetMapping("/byTitle/{title}")
    public String findByTitle(@PathVariable("title") String title, Model model) {
        try {
            Movie laPelicula = movieService.findByTitle(title);
            model.addAttribute("pelicula", laPelicula);
            return "movies/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Title not found");
            return "error";
        }
    }

    @GetMapping("/byDirector/{director}")
    public String findByDirector(@PathVariable("director") String director, Model model) {
        try {
            List<Movie> peliculas = movieService.findByDirector(director);
            model.addAttribute("peliculas", peliculas);
            return "movies/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Director not found");
            return "error";
        }
    }

    @GetMapping("/byGenre/{genre}")
    public String findByGenre(@PathVariable("genre") String genre, Model model) {
        try {
            List<Movie> peliculas = movieService.findByGenre(genre);
            model.addAttribute("peliculas", peliculas);
            return "movies/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Genre not found");
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        try {
            Movie laPelicula = movieService.findById(id).get();
            model.addAttribute("pelicula", laPelicula);
            return "main";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Id not found");
            return "error";
        }
    }

    @PostMapping("/update")
    public String updateMovie(@ModelAttribute Movie movie, Model model) {
        Movie updatedMovie = movieService.updateMovie(movie);
        if (updatedMovie != null) {
            model.addAttribute("pelicula", updatedMovie);
            return "movies/detail";
        } else {
            model.addAttribute("error", "Movie not found");
            return "error";
        }
    }

    @PostMapping("/add")
    public String addMovie(@RequestParam String title, @RequestParam String description, @RequestParam List<String> genre,
                           @RequestParam String director, @RequestParam int duration, int ageRegistration, Model model) {
        try {
            Movie newMovie = movieService.addMovie(title, description, genre, director, duration, ageRegistration);
            if (newMovie == null) {
                throw new InvalidDataException("Invalid Data");
            }
            return "redirect:/movies/list";
        } catch (InvalidDataException e) {
            model.addAttribute("error", "Invalid data");
            return "redirect:/movieAdmin";
        }
    }

    @PostMapping("/delete")
    public String deleteMovie(@RequestParam Long id, Model model) {
        try {
            movieService.deleteMovie(id);
            return "redirect:/movies/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Id not found");
            return "error";
        }
    }
    @GetMapping("/{id}/showtimes")
    public String getShowtimesByMovieId(@PathVariable("id") Long id, Model model) {
        try {
            Movie movie = movieService.findById(id).get();
            List<FilmShow> showtimes = filmShowService.findByMovieId(id);
            model.addAttribute("pelicula", movie);
            model.addAttribute("showtimes", showtimes);
            return "movies/showtimes";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Id not found");
            return "error";
        }
    }
}