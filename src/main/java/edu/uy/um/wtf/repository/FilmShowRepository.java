package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.FilmShow;
import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FilmShowRepository extends JpaRepository<FilmShow, Long> {
    List<FilmShow> findByDate(Date date);

    List<FilmShow> findByLanguage(String language);

    List<FilmShow> findBySpecialEffects(String specialEffects);

    List<FilmShow> findByRoom(Room room);

    List<FilmShow> findByMovie(Movie movie);

    Optional<FilmShow> findById(Long id);
}
