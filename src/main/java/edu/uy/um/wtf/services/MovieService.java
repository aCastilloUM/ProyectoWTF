package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(String title, List<String> genre, String director, int duration) {
        if (title == null || title.isEmpty() || genre == null || genre.isEmpty() || director == null || director.isEmpty()) {
            return null; //Lanzar excepcion
        }
        if (duration < 0) {
            return null; //Lanzar excepcion
        }
        if (movieRepository.findByTitle(title).isPresent()) {
            return null; //Lanzar excepcion
        }
        if (title.trim().equals("") || director.trim().equals("")) {
            return null; //Lanzar excepcion
        }

        Movie movie = Movie.builder()
                .title(title)
                .director(director)
                .genre(genre)
                .duration(duration)
                .build();

        return movieRepository.save(movie);
    }
}
