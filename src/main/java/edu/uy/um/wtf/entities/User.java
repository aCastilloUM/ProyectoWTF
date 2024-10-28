package edu.uy.um.wtf.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @NotNull
    @Column(name = "userName")
    private String userName;

    @NotNull
    @Column(name = "Password")
    private String password;
}
