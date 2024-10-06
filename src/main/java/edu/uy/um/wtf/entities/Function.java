package edu.uy.um.wtf.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Function")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Function {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Time")
    private LocalTime time;

    @Column(name = "Cinema Branch")
    private Branch branch;

    @Column(name = "Movie")
    private Movie movie;

    @Column(name = "Room")
    private Room room;

    @Column(name = "Special Effects")
    private String specialEffects;

    @Column(name = "Language")
    private String language;

    @Column(name = "Duration")
    private int duration;

}
