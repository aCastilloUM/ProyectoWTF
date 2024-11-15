package edu.uy.um.wtf.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Movie")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "director")
    private String director;

    @ElementCollection
    @CollectionTable(name = "Movie_Genres", joinColumns = @JoinColumn(name = "Movie_Id"))
    @Column(name = "genre")
    private List<String> genre;

    @Column(name = "duration")
    private int duration;

    @Column(name = "ageRestriction")
    private Integer ageRegistration = 0;

    @Column(name = "posterPath")
    private String posterPath;

    @Column(name = "releaseDate")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
}