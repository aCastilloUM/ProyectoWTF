package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.PeymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PeymentMethod, Long> {
    Optional<PeymentMethod> findByCardNumber(Long cardNumber);
}