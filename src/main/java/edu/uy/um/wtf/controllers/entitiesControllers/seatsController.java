package edu.uy.um.wtf.controllers.entitiesControllers;

import edu.uy.um.wtf.entities.Seats;
import edu.uy.um.wtf.services.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/seats")
public class seatsController {

    @Autowired
    private SeatsService seatsService;

    @GetMapping("/showSeats/{filmShowId}")
    public String showSeats(@PathVariable Long filmShowId, Model model) {
        List<Seats> asientos = seatsService.getSeatsByFilmShowId(filmShowId);
        asientos.sort(Comparator.comparing(Seats::getId));

        for (Seats seat : asientos) {
            if (seat.isOccupied() == true) {
                System.out.println("Seat " + seat.getId() + " is occupied");
            }
        }
        List<List<Seats>> filas = new ArrayList<>();
        for (int i = 0; i < asientos.size(); i += 10) {
            filas.add(asientos.subList(i, Math.min(i + 10, asientos.size())));
        }

        model.addAttribute("asientos", filas);
        model.addAttribute("filmShowId", filmShowId);
        return "seats";
    }

    @PostMapping("/confirmReservation")
    public String confirmReservation(@RequestParam Long filmShowId, @RequestParam("selectedSeats") String selectedSeats) {
        List<Long> seatIds = new ArrayList<>();
        for (String id : selectedSeats.split(",")) {
            if (!id.trim().isEmpty() && id.matches("\\d+")) {
                try {
                    seatIds.add(Long.parseLong(id));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        seatsService.reserveSeats(filmShowId, seatIds);
        return "redirect:/snacks/snack";
    }
}
