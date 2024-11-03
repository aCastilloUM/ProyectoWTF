package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.PaymentMethod;
import edu.uy.um.wtf.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentRepository;

    public void savePaymentMethod(PaymentMethod paymentMethod) {
        paymentRepository.save(paymentMethod);
    }

    public Optional<PaymentMethod> findByCardNumber(Long cardNumber){
        return paymentRepository.findByCardNumber(cardNumber);
    }

    public List<PaymentMethod> getAll(){
        return paymentRepository.findAll();
    }
}