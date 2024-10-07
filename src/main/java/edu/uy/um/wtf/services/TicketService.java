package edu.uy.um.wtf.services;


import edu.uy.um.wtf.entities.FilmShow;
import edu.uy.um.wtf.entities.Ticket;
import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket newTicket (User client, FilmShow filmshow, int row, int column) {
        if (client == null || filmshow == null || row < 0 || column < 0) {
            return null;
        }
        if (ticketRepository.findByRowandColumn(row, column) == null) {
            Ticket ticket = Ticket.builder()
                    .client(client)
                    .filmShow(filmshow)
                    .row(row)
                    .column(column)
                    .build();

            return ticketRepository.save(ticket);
        }
        else {
            return null;
        }
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public List<Ticket> findByClient(User client) {
        if (client == null) {
            return null;
        }
        return ticketRepository.findByClient(client);
    }

    public List<Ticket> findByFilmShow(FilmShow filmshow) {
        if (filmshow == null) {
            return null;
        }
        return ticketRepository.findByFilmShow(filmshow);
    }

    public Ticket findbyRow (int row) {
        if (row < 0) {
            return null;
        }
        return ticketRepository.findByRow(row);
    }

    public Ticket findbyColumn (int column) {
        if (column < 0) {
            return null;
        }
        return ticketRepository.findByColumn(column);
    }

    public Ticket findByRowandColumn (int row, int column) {
        if (row < 0 || column < 0) {
            return null;
        }
        if (ticketRepository.findByRowandColumn(row, column) == null) {
            return null;
        }
        return ticketRepository.findByRowandColumn(row, column);
    }

}
