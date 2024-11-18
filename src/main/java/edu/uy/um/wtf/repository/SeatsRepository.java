package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SeatsRepository extends JpaRepository<Seats, Long> {

    List<Seats> findByFilmShowId(Long filmShowId);
}
