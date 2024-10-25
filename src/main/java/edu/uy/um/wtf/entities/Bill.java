package edu.uy.um.wtf.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Bill")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Client_Id", nullable = false)
    private User client;

    @OneToMany
    @JoinColumn(name = "Bill_Id")
    private List<Snack> snacks;

    @Column(name = "TotalSnacks")
    private double totalSnacks;

    @OneToMany
    @JoinColumn(name = "Bill_Id")
    private List<Ticket> tickets;

    @Column(name = "TotalTickets")
    private double totalTickets;

    @Column(name = "Total")
    private double total;
}