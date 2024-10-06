package edu.uy.um.wtf.entities;


import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Room")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Room {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Number")
    private int number;

    @Column(name = "Capacity")
    private int capacity;

    @Column(name = "Rows")
    private int rows;

    @Column(name = "Columns")
    private int columns;
}
