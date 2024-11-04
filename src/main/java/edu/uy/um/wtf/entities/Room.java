package edu.uy.um.wtf.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Room")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "number", unique = true)
    private int number;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "rows") //15
    private int rows;

    @Column(name = "columns") //10
    private int columns;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;
}
