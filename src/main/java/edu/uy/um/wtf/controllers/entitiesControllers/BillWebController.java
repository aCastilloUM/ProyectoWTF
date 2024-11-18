package edu.uy.um.wtf.controllers.entitiesControllers;

import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.entities.Ticket;
import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import edu.uy.um.wtf.entities.Bill;
import edu.uy.um.wtf.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/bills")
public class BillWebController {

    @Autowired
    private BillService billService;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Bill> bills = billService.getAll();
        model.addAttribute("bills", bills);
        return "bills/list";
    }

    @PostMapping("/paymentSuccess")
    public String paymentSuccess(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/logIn";
        }

        Bill bill = (Bill) session.getAttribute("bill");
        if (bill == null) {
            return "redirect:/snacks";
        }

        Movie movie = (Movie) session.getAttribute("movie");
        List<String> seats = (List<String>) session.getAttribute("seats");

        model.addAttribute("bill", bill);
        model.addAttribute("movie", movie);
        model.addAttribute("seats", seats);
        return "paymentSuccess";
    }

    @GetMapping("/sixMonths")public String sixMonth(Model model, HttpSession session) {
        Bill bill = (Bill) session.getAttribute("bill");
        LocalDate actualDate = LocalDate.now();
        LocalDate limitDate = LocalDate.of(2025, 5, 15);
        if (actualDate.isBefore(limitDate)) {
            List<Ticket> tickets = bill.getTickets();
            for (int i=0; i<tickets.size(); i++) {
                tickets.get(i).setPrice(0);
            }
        return "error";
        } else {
            return "error";    }}

    @PostMapping("/processPayment")
    public String processPayment(@RequestParam String paymentMethod, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/logIn";
        }

        Bill bill = (Bill) session.getAttribute("bill");
        if (bill == null) {
            return "redirect:/snacks";
        }


        Movie movie = (Movie) session.getAttribute("movie");
        List<String> seats = (List<String>) session.getAttribute("seats");

        model.addAttribute("bill", bill);
        model.addAttribute("movie", movie);
        model.addAttribute("seats", seats);
        return "/paymentSuccess";
    }
}
