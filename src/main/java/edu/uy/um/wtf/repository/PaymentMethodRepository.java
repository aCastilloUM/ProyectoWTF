package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.PaymentMethod;
import edu.uy.um.wtf.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    Optional<PaymentMethod> findByCardNumber(Long cardNumber);

    List<PaymentMethod> findByUser(User user);
}