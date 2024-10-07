package edu.uy.um.wtf.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Ticket")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Ticket {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Client")
    private User client;

    @Column(name = "FilmShow")
    private FilmShow filmShow;

    @Column(name = "Price")
    private double price;

    @Column(name = "Row")
    private int row;

    @Column(name = "Column")
    private int column;

    @Column(name = "Room")
    private Room room;

    @Column(name = "Movie")
    private Movie movie;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Time")
    private LocalTime time;
}


