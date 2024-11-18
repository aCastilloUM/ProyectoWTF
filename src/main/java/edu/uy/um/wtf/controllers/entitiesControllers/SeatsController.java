package edu.uy.um.wtf.controllers.entitiesControllers;

import edu.uy.um.wtf.entities.FilmShow;
import edu.uy.um.wtf.entities.Seats;
import edu.uy.um.wtf.entities.Snack;
import edu.uy.um.wtf.services.FilmShowService;
import edu.uy.um.wtf.services.SeatsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/seats")
public class SeatsController {

    @Autowired
    private SeatsService seatsService;
    @Autowired
    private FilmShowService filmShowService;

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
    public String confirmReservation(@RequestParam Long filmShowId,
                                     @RequestParam("selectedSeats") String selectedSeats,
                                     HttpSession session) {
        List<Long> seatIds = new ArrayList<>();
        for (String id : selectedSeats.split(",")) {
            if (!id.trim().isEmpty() && id.matches("\\d+")) {
                seatIds.add(Long.parseLong(id));
            }
        }

        seatsService.reserveSeats(filmShowId, seatIds);

        // Crea una lista para almacenar los detalles de fila y columna de los asientos reservados
        List<Map<String, Integer>> reservedSeatsDetails = new ArrayList<>();

        for (Long seatId : seatIds) {
            Seats seat = seatsService.getSeatById(seatId); // Necesitarás un método para obtener un asiento por su ID
            Map<String, Integer> seatPosition = new HashMap<>();
            seatPosition.put("row", seat.getRow());
            seatPosition.put("column", seat.getColumn());
            reservedSeatsDetails.add(seatPosition);
        }


        // Guarda en la sesión los detalles de los asientos reservados
        session.setAttribute("reservedSeatsDetails", reservedSeatsDetails);

        session.setAttribute("movie", filmShowService.findById(filmShowId).get().getMovie());


        return "redirect:/snacks/snack";
    }
}
