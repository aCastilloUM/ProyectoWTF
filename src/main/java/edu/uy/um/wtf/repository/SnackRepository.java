package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.Snack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SnackRepository extends JpaRepository<Snack, Long> {
    Optional<Snack> findByName(String name);

    List<Snack> findByType(String type);
}