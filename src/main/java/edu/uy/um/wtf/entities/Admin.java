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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first name")
    private String firstName;

    @Column(name = "last name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "mail")
    private String mail;

    @NotNull
    @Column(name = "username")
    private String userName;

    @NotNull
    @Column(name = "password")
    private String password;

    @Override
    public String toString() {
        return (new StringBuilder())
                .append("\tAdmin{Id: ")
                .append(id)
                .append("\t, First name: ")
                .append(firstName)
                .append("\t, Last name: ")
                .append(lastName)
                .append("\t, Birthdate: ")
                .append(birthDate)
                .append("\t, Mail: ")
                .append(mail)
                .append("\t, UserName: ")
                .append(userName)
                .append("\t, Password: ")
                .append(password)
                .append("}")
                .toString();
    }
}