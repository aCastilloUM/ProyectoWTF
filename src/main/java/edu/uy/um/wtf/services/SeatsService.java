package edu.uy.um.wtf.services;


import edu.uy.um.wtf.entities.Seats;
import edu.uy.um.wtf.repository.SeatsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatsService {

    @Autowired
    private SeatsRepository seatsRepository;


    public List<Seats> getSeatsByFilmShowId(Long filmShowId) {
        return seatsRepository.findByFilmShowId(filmShowId);
    }

    @Transactional
    public void reserveSeats(Long filmShowId, List<Long> selectedSeats) {
        List<Seats> seatsToReserve = seatsRepository.findAllById(selectedSeats);
        seatsToReserve.forEach(seat -> seat.setOccupied(true));
        seatsRepository.saveAll(seatsToReserve);
    }
}
