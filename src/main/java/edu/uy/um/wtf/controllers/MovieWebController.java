/*
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

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Movie> peliculas = movieService.getAll();
        model.addAttribute("peliculas", peliculas);
        return "movies/list";
    }

    @GetMapping("/byTitle")
    public String findByTitle(@PathVariable("Titulo") String title, Model model) {
        try {
            Movie laPelicula = movieService.findByTitle(title).get();
            model.addAttribute("pelicula", laPelicula);
            return "movies/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Title not found");
            return "error";
        }
    }

    @GetMapping("/byDirector")
    public String findByDirector(@PathVariable("Director") String director, Model model) {
        try {
            List<Movie> peliculas = movieService.findByDirector(director);
            model.addAttribute("peliculas", peliculas);
            return "movies/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Director not found");
            return "error";
        }
    }

    @GetMapping("/byGenre")
    public String findByGenre(@PathVariable("Genero") String genre, Model model) {
        try {
            List<Movie> peliculas = movieService.findByGenre(genre);
            model.addAttribute("peliculas", peliculas);
            return "movies/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Genre not found");
            return "error";
        }
    }

    @GetMapping("/byId")
    public String findById(@PathVariable("Id") Long id, Model model) {
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
}

 */