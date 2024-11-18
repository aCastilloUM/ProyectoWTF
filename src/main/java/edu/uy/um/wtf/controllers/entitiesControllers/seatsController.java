package edu.uy.um.wtf.controllers.entitiesControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seats")
public class SeatsController {

    @GetMapping("/showSeats")
    public String mostrarAsientos(Model model) {
        boolean[][] asientos = new boolean[15][10];  // Todos los asientos disponibles
        model.addAttribute("asientos", asientos);
        return "seats";

    }
}
