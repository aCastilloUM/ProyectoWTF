package edu.uy.um.wtf.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Ticket")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Client_Id", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "FilmShow_Id", nullable = false)
    private FilmShow filmShow;

    @Column(name = "Price")
    private double price;

    @Column(name = "Row")
    private int row;

    @Column(name = "seatColumn")
    private int seatColumn;

    @ManyToOne
    @JoinColumn(name = "Room_Id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "Movie_Id", nullable = false)
    private Movie movie;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Time")
    private LocalTime time;
}