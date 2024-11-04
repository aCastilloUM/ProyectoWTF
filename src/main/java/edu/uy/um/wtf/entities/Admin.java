package edu.uy.um.wtf.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Admin")
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "first name")
    private String firstName;

    @Column(name = "last name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "mail", unique = true)
    private String mail;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

}