package edu.uy.um.wtf.entities;


import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Branch")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Branch {

    @Id
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Location")
    private String location;

    @Column(name = "Rooms")
    private int rooms;

}
