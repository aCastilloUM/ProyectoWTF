package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.FilmShow;
import edu.uy.um.wtf.entities.Room;
import edu.uy.um.wtf.entities.Ticket;
import edu.uy.um.wtf.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByDate(Date date);

    List<Ticket> findByRoom(Room room);

    List<Ticket> findByUser(User user);

    Ticket findByRowandColumn(int row, int column);

    Ticket findByColumn(int column);

    Ticket findByRow(int row);

    List<Ticket> findByClient(User client);

    List<Ticket> findByFilmShow(FilmShow filmshow);
}