package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.Function;
import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FunctionRepository extends JpaRepository<Function, Long> {
    List<Function> findByDate(Date date);

    List<Function> findByLanguage(String language);

    List<Function> findBySpecialEffects(String specialEffects);

    List<Function> findByRoom(Room room);

    List<Function> findByMovie(Movie movie);
}
