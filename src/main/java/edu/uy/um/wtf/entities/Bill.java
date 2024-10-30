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
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @OneToMany
    @JoinColumn(name = "bill_id")
    private List<Snack> snacks;

    @Column(name = "total snacks")
    private double totalSnacks;

    @OneToMany
    @JoinColumn(name = "bill_id")
    private List<Ticket> tickets;

    @Column(name = "total tickets")
    private double totalTickets;

    @Column(name = "total")
    private double total;
}