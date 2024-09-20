package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByMail(String mail);
}
