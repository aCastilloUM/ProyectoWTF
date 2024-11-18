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
import java.util.Optional;

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

    @GetMapping("/movies/{movieId}")
    public String getFilmShowsByMovie(@PathVariable Long movieId, Model model) {
        Optional<Movie> movieOpt = movieService.findById(movieId);
        if (movieOpt.isPresent()) {
            List<FilmShow> filmShows = filmShowService.findByMovie(movieOpt.get());
            model.addAttribute("filmShows", filmShows);
            model.addAttribute("selectedMovie", movieOpt.get());
            return "users/main";  // Regresa a la página principal o una página específica de detalles
        } else {
            model.addAttribute("error", "Movie not found");
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
                           @RequestParam String director, @RequestParam int duration, @RequestParam int ageRegistration, @RequestParam String posterPath, Model model) {
        try {
            Movie newMovie = movieService.addMovie(title, description, genre, director, duration, ageRegistration, posterPath);
            if (newMovie == null) {
                throw new InvalidDataException("Invalid Data");
            }
            return "mainAdmin";
        } catch (InvalidDataException e) {
            model.addAttribute("error", "Invalid data");
            return "mainAdmin";
        }
    }

    @PostMapping("/delete")
    public String deleteMovie(@RequestParam Long id, Model model) {
        try {
            movieService.deleteMovie(id);
            return "mainAdmin";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Id not found");
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String getShowtimesByMovieId(@PathVariable("id") Long id, Model model) {
        try {
            Movie movie = movieService.findById(id).get();
            List<FilmShow> showtimes = filmShowService.findByMovieId(id);
            model.addAttribute("pelicula", movie);
            model.addAttribute("showtimes", showtimes);
            return "main";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Id not found");
            return "error";
        }
    }

    @GetMapping("/info/{id}")
    public String showMovieInfo(@PathVariable Long id, Model model) {
        Optional<Movie> movie = movieService.findById(id);
        if (movie.isPresent()) {
            model.addAttribute("movie", movie.get());
            return "movieInfo";
        } else {
            model.addAttribute("error", "Movie not found");
            return "error";
        }
    }
}