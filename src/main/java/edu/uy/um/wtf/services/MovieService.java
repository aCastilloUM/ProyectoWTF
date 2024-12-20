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

    public Movie addMovie(String title, String description, List<String> genre, String director, int duration, int ageRegistration, String posterPath) {
        if (title == null || title.isEmpty() || description == null || description.isEmpty() || genre == null ||
                genre.isEmpty() || director == null || director.isEmpty() || duration <= 0 || ageRegistration < 0) {
            return null;
        }
        if (duration < 0) {
            return null;
        }
        if (movieRepository.findByTitle(title) != null) {
            return null;
        }
        if (title.trim().equals("") || director.trim().equals("")) {
            return null;
        }

        Movie movie = Movie.builder()
                .title(title)
                .description(description)
                .director(director)
                .genre(genre)
                .duration(duration)
                .ageRegistration(ageRegistration)
                .posterPath(posterPath)
                .build();

        return movieRepository.save(movie);
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Movie findByTitle(String title) {
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
        return movieRepository.save(movie);
    }


    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public boolean canPurchaseTicket(Long movieId, int userAge) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        return movie.map(value -> userAge >= value.getAgeRegistration()).orElse(false);
    }

    public Optional<Movie> getById(Long id) {
        return movieRepository.findById(id);
    }
}
