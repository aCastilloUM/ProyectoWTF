package edu.uy.um.wtf.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Payment method")
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentMethod {

    @Id
    @Column(name = "card number", unique = true)
    private Long cardNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "holder name")
    private String holderName;

    @Column(name = "expiration date")
    private String expiryDate;

    @Column(name = "cvv")
    private String cvv;


}