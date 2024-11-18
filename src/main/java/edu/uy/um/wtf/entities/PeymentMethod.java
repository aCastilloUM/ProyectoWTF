package edu.uy.um.wtf.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Date;

@Entity
@Table(name = "Payment method")
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PeymentMethod {

    @Id
    @Column(name = "card number", unique = true)
    private Long cardNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "holder name")
    private String holderName;

    @Column(name = "expiration date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Column(name = "cvv")
    private String cvv;


}