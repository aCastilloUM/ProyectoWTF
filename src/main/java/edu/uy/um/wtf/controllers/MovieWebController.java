package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.exceptions.InvalidDataException;
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

    @GetMapping("/list")
    public String getAllMovies(Model model) {
        List<Movie> movies = movieService.getAll();
        model.addAttribute("movies", movies);
        return "main";
    }

    @GetMapping("/byTitle/{title}")
    public String findByTitle(@PathVariable("title") String title, Model model) {
        try {
            Movie laPelicula = movieService.findByTitle(title).get();
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
            return "movies/detail";
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
    public String addMovie(@RequestParam String title, @RequestParam List<String> director,
                           @RequestParam String genre, @RequestParam int duration, Model model) {
        try {
            Movie movie = movieService.addMovie(title, director, genre, duration);
            if (movie == null) {
                throw new InvalidDataException("Invalid Data");
            }
            model.addAttribute("pelicula", movie);
            return "movies/detail";
        } catch (InvalidDataException e) {
            model.addAttribute("error", "Invalid data");
            return "error";
        }
    }
}