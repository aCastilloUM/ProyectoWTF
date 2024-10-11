package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);

    List<Movie> findByDirector(String director);

    List<Movie> findByGenre(String genre);
}
