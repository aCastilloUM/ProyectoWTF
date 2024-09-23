package edu.uy.um.wtf.entities;

import jakarta.persistence.*;
        import lombok.*;

        import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Admin")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @Column(name = "Id")
    private Long id;

    @Column(name = "First Name")
    private String firstName;

    @Column(name = "Last Name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "Birthdate")
    private Date birthDate;

    @Column(name = "Age")
    private int age;

    @Override
    public String toString()
    {
        return (new StringBuilder())
                .append("\tAdmin{Id: ")
                .append(id)
                .append("\t, First name: ")
                .append(firstName)
                .append("\t, Last name: ")
                .append(lastName)
                .append("\t, Birthdate: ")
                .append( birthDate )
                .append("\t, Age: ")
                .append( age )
                .append("}")
                .toString();
    }
}