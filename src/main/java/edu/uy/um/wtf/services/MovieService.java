package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(String title, List<String> genre, String director, int duration) {
        if (title == null || title.isEmpty() || genre == null || genre.isEmpty() || director == null || director.isEmpty()) {
            return null;
        }
        if (duration < 0) {
            return null;
        }
        if (movieRepository.findByTitle(title).isPresent()) {
            return null;
        }
        if (title.trim().equals("") || director.trim().equals("")) {
            return null;
        }

        Movie movie = Movie.builder()
                .title(title)
                .director(director)
                .genre(genre)
                .duration(duration)
                .build();

        return movieRepository.save(movie);
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findByTitle(String title) {
        if (title == null || title.isEmpty()) {
            return null;
        }
        return movieRepository.findByTitle(title);
    }

    public Optional<Movie> findById(Long id) {
        if (id == null) {
            return null;
        }
        return movieRepository.findById(id);
    }

    public List<Movie> findByDirector(String director) {
        if (director == null || director.isEmpty()) {
            return null;
        }
        return movieRepository.findByDirector(director);
    }

    public List<Movie> findByGenre(String genre) {
        if (genre == null || genre.isEmpty()) {
            return null;
        }
        return movieRepository.findByGenre(genre);
    }

    public Movie updateMovie(Movie movie) {
        if (movieRepository.existsById(movie.getId())) {
            return movieRepository.save(movie);
        }
        return null;
    }



}
