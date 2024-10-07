package edu.uy.um.wtf.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Bill")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Bill {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Client")
    private User client;

    @ElementCollection
    @CollectionTable(name = "Bill_Snacks", joinColumns = @JoinColumn(name = "Bill"))
    @Column(name = "Snack")
    private List<Snack> snacks;

    @Column(name = "TotalSnacks")
    private double totalSnacks;

    @ElementCollection
    @CollectionTable(name = "Bill_Tickets", joinColumns = @JoinColumn(name = "Bill"))
    @Column(name = "Ticket")
    private List<Ticket> tickets;

    @Column(name = "TotalTickets")
    private double totalTickets;

    @Column(name = "Total")
    private double total;
}
