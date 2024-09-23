package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    public Admin findByID(Long id);

}
