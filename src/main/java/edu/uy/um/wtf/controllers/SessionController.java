package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.entities.PeymentMethod;
import edu.uy.um.wtf.entities.Snack;
import edu.uy.um.wtf.entities.Ticket;
import edu.uy.um.wtf.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.attribute.standard.JobKOctets;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    public SessionService sessionService;


    @PostMapping("/addPeymentMethod")
    public String addPeymentMethod(String key, PeymentMethod peymentMethod) {
        if (peymentMethod == null) {
            return "redirect:/peymentMethods";
        }
        sessionService.addAttribute(key, peymentMethod);
        return "redirect:/peymentMethods";
    }

    @PostMapping("/deletePeymentMethod")
    public String deletePeymentMethod(String key) {
        sessionService.removeAttribute(key);
        return "redirect:/peymentMethods";
    }

    @PostMapping("/addSnack")
    public String addSnack(String key, Snack snack) {

        List<Snack> snacks = (List<Snack>) sessionService.getAttribute(key);
        if (snacks == null) {
            snacks = new ArrayList<>();
        }
        snacks.add(snack);
        sessionService.addAttribute(key, snacks);

        return "redirect:/snacks";
    }

    @PostMapping("/deleteSnack")
    public String deleteSnack(String key) {

        List<Snack> snacks;
        try {
            snacks = (List<Snack>) sessionService.getAttribute(key);
            sessionService.addAttribute(key, snacks);
        } catch (Exception e) {
            return "redirect:/snacks";
        }

        for (int i=0; i<snacks.size(); i++) {
            if (snacks.get(i).getName() == key) {
                snacks.remove(i);
                break;
            }
        }
        return "redirect:/snacks";
    }

    @PostMapping("/addTicket")
    public String addTicket(String key, Ticket ticket) {

        List<Ticket> tickets = (List<Ticket>) sessionService.getAttribute(key);
        if (tickets == null) {
            tickets = new ArrayList<>();
        }
        tickets.add(ticket);
        sessionService.addAttribute(key, tickets);

        return "redirect:/tickets";
    }

    @PostMapping("/deleteTicket")
    public String deleteTicket(String key) {

        List<Ticket> tickets;
        try {
            tickets = (List<Ticket>) sessionService.getAttribute(key);
            sessionService.addAttribute(key, tickets);
        } catch (Exception e) {
            return "redirect:/tickets";
        }

        for (int i=0; i<tickets.size(); i++) {
            if (tickets.get(i).getMovie().getTitle() == key) {
                tickets.remove(i);
                break;
            }
        }
        return "redirect:/tickets";
    }

    @PostMapping("/addMovie")
    public String addMovie(String key, Movie movie) {

        List<Movie> movies = (List<Movie>) sessionService.getAttribute(key);
        if (movies == null) {
            movies = new ArrayList<>();
        }
        movies.add(movie);
        sessionService.addAttribute(key, movies);

        return "redirect:/movies";
    }

    @PostMapping("/deleteMovie")
    public String deleteMovie(String key) {

        List<Movie> movies;
        try {
            movies = (List<Movie>) sessionService.getAttribute(key);
            sessionService.addAttribute(key, movies);
        } catch (Exception e) {
            return "redirect:/movies";
        }

        for (int i=0; i<movies.size(); i++) {
            if (movies.get(i).getTitle() == key) {
                movies.remove(i);
                break;
            }
        }
        return "redirect:/movies";
    }




    @PostMapping("/invalidate")
    public String invalidate() {
        sessionService.invalidate();
        return "redirect:/";
    }


}
