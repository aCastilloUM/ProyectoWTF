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
    @Column(name = "card number")
    private Long cardNumber;

    //Hacer que se relacione con el user
    @Column(name = "holder name")
    private String holderName;

    @Column(name = "expiration date")
    private Date expirationDate;

    @Column(name = "cvv")
    private Integer cvv;
}
