package edu.uy.um.wtf.controllers;


import edu.uy.um.wtf.entities.FilmShow;
import edu.uy.um.wtf.entities.Room;
import edu.uy.um.wtf.entities.User;
import org.springframework.ui.Model;
import edu.uy.um.wtf.entities.Ticket;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketWebController{

    @Autowired
    private TicketService ticketService;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Ticket> tickets = ticketService.getAll();
        model.addAttribute("tickets", tickets);
        return "tickets/list";
    }

    @GetMapping("/byId{Id}")
    public String findById(@PathVariable("Id") Long id, Model model) {
        try {
            Ticket ticket = ticketService.findById(id).orElseThrow(EntityNotFoundException::new);
            model.addAttribute("ticket", ticket);
            return "tickets/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Id not found");
            return "error";
        }
    }

    @GetMapping("/byDate{Date}")
    public String findByDate(@PathVariable("Date") Date date, Model model) {
        if (date == null) {
            model.addAttribute("error", "Date not found");
            return "error";
        }
        try {
            List<Ticket> tickets = ticketService.findByDate(date);
            model.addAttribute("tickets", tickets);
            return "tickets/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Date not found");
            return "error";
        }
    }

    @GetMapping("/byRoom{Room}")
    public String findByRoom(@PathVariable("Room") Room room, Model model) {
        if (room == null) {
            model.addAttribute("error", "Room not found");
            return "error";
        }
        try {
            List<Ticket> tickets = ticketService.findByRoom(room);
            model.addAttribute("tickets", tickets);
            return "tickets/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Room not found");
            return "error";
        }
    }

    @GetMapping("/byFilmShow{FilmShow}")
    public String findByFilmShow(@PathVariable("FilmShow") FilmShow filmShow, Model model) {
        if (filmShow == null) {
            model.addAttribute("error", "FilmShow not found");
            return "error";
        }
        try {
            List<Ticket> tickets = ticketService.findByFilmShow(filmShow);
            model.addAttribute("tickets", tickets);
            return "tickets/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "FilmShow not found");
            return "error";
        }
    }

    @GetMapping("/byUser{User}")
    public String findByUser(@PathVariable("User") User user, Model model) {
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "error";
        }
        try {
            List<Ticket> tickets = ticketService.findByUser(user);
            model.addAttribute("tickets", tickets);
            return "tickets/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "User not found");
            return "error";
        }
    }


}
