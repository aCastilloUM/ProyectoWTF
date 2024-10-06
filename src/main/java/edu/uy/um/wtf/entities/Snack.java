package edu.uy.um.wtf.entities;


import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Snack")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Snack {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private double price;

    @Column(name = "Stock")
    private int stock;

}
