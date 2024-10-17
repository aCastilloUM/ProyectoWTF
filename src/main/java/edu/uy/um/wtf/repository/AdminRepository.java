package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findById(Long id);

    List<Object> findByFirstNameAndLastName(String firstName, String lastName);
}