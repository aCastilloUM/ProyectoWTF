package edu.uy.um.wtf.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "seats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seats implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "seats_row")
    private int row;

    @Column(name = "seats_column")
    private int column;

    @Column(name = "occupied")
    private boolean occupied;

    @ManyToOne
    @JoinColumn(name = "film_show_id")
    private FilmShow filmShow;



}
