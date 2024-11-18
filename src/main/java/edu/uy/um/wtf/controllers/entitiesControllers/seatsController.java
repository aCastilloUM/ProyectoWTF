package edu.uy.um.wtf.controllers.entitiesControllers;

import edu.uy.um.wtf.entities.Seats;
import edu.uy.um.wtf.services.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/seats")
public class seatsController {

    @Autowired
    private SeatsService seatsService;

    @GetMapping("/showSeats/{filmShowId}")
    public String showSeats(@PathVariable Long filmShowId, Model model) {
        List<Seats> asientos = seatsService.getSeatsByFilmShowId(filmShowId);
        List<List<Seats>> filas = new ArrayList<>();
        for (int i = 0; i < asientos.size(); i += 10) {
            filas.add(asientos.subList(i, Math.min(i + 10, asientos.size())));
        }

        model.addAttribute("asientos", filas);
        model.addAttribute("filmShowId", filmShowId);
        return "seats";
    }

    @PostMapping("/confirmReservation")
    public String confirmReservation(Long filmShowId, List<Long> selectedSeats) {
        seatsService.reserveSeats(filmShowId, selectedSeats);
        return "redirect:/seats/showSeats/" + filmShowId;
    }
}
