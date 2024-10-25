package edu.uy.um.wtf.entities;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "FilmShow")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class FilmShow implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Time")
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "Branch_Id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "Movie_Id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "Room_Id")
    private Room room;

    @Column(name = "Special_Effects")
    private String specialEffects;

    @Column(name = "Language")
    private String language;

    @Column(name = "Duration")
    private int duration;

}
