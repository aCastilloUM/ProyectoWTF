package edu.uy.um.wtf.controllers;


import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.exceptions.InvalidDataException;
import edu.uy.um.wtf.services.MovieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.uy.um.wtf.responses.ErrorResponse;


import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieRestController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAll(){
        List<Movie> peliculas = movieService.getAll();
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/byTitle{Title}")
    public ResponseEntity<?> findByTitle(@PathVariable("Titulo") String title) {
        try {
            Movie laPelicula = movieService.findByTitle(title).get();
            return ResponseEntity.ok(laPelicula);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse("Title not found"));
        }
    }

    @GetMapping("/byDirector{Director}")
    public ResponseEntity<?> findByDirector(@PathVariable("Director") String director) {
        try {
            List<Movie> peliculas = movieService.findByDirector(director);
            return ResponseEntity.ok(peliculas);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse("Director not found"));
        }
    }

    @GetMapping("/byGenre{Id}")
    public ResponseEntity<?> findByGenre(@PathVariable("Genero") String genre) {
        try {
            List<Movie> peliculas = movieService.findByGenre(genre);
            return ResponseEntity.ok(peliculas);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorResponse("Genre not found"));
        }
    }

    @GetMapping("/byId{Id}")
    public ResponseEntity<?> findById(@PathVariable("Id") Long id) {
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
