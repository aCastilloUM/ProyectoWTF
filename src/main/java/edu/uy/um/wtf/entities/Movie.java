package edu.uy.um.wtf.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "Movie")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Movie implements Serializable
{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Director")
    private String director;

    @ElementCollection
    @CollectionTable(name = "Movie_Genres", joinColumns = @JoinColumn(name = "Movie_Id"))
    @Column(name = "Genre")
    private List<String> genre;

    @Column(name = "Duration")
    private int duration;



}
