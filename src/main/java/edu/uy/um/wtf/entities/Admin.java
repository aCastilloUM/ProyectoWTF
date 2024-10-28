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
    @Column(name = "Id")
    private Long id;

    @Column(name = "First Name")
    private String firstName;

    @Column(name = "Last Name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "Birthdate")
    private Date birthDate;

    @Column(name = "Mail")
    private String mail;

    @NotNull
    @Column(name = "UserName")
    private String userName;

    @NotNull
    @Column(name = "Password")
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
                .append("\t, User name: ")
                .append(userName)
                .append("\t, Password: ")
                .append(password)
                .append("}")
                .toString();
    }
}