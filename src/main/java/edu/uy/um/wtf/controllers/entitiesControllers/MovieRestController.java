package edu.uy.um.wtf.controllers.entitiesControllers;

import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.exceptions.InvalidDataException;
import edu.uy.um.wtf.responses.ErrorResponse;
import edu.uy.um.wtf.services.MovieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAll() {
        List<Movie> peliculas = movieService.getAll();
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/byTitle/{title}")
    public ResponseEntity<?> findByTitle(@PathVariable("title") String title) {
        try {
            Movie laPelicula = movieService.findByTitle(title);
            return ResponseEntity.ok(laPelicula);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse("Title not found"));
        }
    }

    @GetMapping("/byDirector/{director}")
    public ResponseEntity<?> findByDirector(@PathVariable("director") String director) {
        try {
            List<Movie> peliculas = movieService.findByDirector(director);
            return ResponseEntity.ok(peliculas);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse("Director not found"));
        }
    }

    @GetMapping("/byGenre/{genre}")
    public ResponseEntity<?> findByGenre(@PathVariable("genre") String genre) {
        try {
            List<Movie> peliculas = movieService.findByGenre(genre);
            return ResponseEntity.ok(peliculas);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse("Genre not found"));
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            Movie laPelicula = movieService.findById(id).get();
            return ResponseEntity.ok(laPelicula);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse("Id not found"));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie) {
        Movie updatedMovie = movieService.updateMovie(movie);
        if (updatedMovie != null) {
            return ResponseEntity.ok(updatedMovie);
        } else {
            return ResponseEntity.status(404).body(new ErrorResponse("Movie not found"));
        }
    }
}