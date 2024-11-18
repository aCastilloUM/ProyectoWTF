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