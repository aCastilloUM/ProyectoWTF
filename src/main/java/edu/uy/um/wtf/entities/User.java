package edu.uy.um.wtf.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Users")
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "first name")
    private String firstName;

    @Column(name = "last name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth date")
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
