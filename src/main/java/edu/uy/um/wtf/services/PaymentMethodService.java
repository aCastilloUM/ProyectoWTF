package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.PeymentMethod;
import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentRepository;

    public void savePaymentMethod(PeymentMethod paymentMethod) {
        paymentRepository.save(paymentMethod);
    }

    public List<PeymentMethod> findByUser(User user){
        return paymentRepository.findByUser(user);
    }
    public Optional<PeymentMethod> findByCardNumber(Long cardNumber){
        return paymentRepository.findByCardNumber(cardNumber);
    }

    public List<PeymentMethod> getAll(){
        return paymentRepository.findAll();
    }
}