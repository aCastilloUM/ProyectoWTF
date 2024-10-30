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
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "type")
    private String type;

}
