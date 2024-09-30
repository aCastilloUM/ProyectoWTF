package edu.uy.um.wtf.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Clients")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable {
    @Id
    @Column(name = "Id")
    private Long id;

    @Column(name = "First Name")
    private String firstName;

    @Column(name = "Last Name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "Birth Date")
    private Date birthDate;

    @Column(name = "Mail")
    private String mail;
}
