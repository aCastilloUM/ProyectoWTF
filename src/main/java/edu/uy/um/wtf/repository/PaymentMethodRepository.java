package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.PeymentMethod;
import edu.uy.um.wtf.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PeymentMethod, Long> {
    Optional<PeymentMethod> findByCardNumber(Long cardNumber);

    List<PeymentMethod> findByUser(User user);
}