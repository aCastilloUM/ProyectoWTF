package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.entities.PaymentMethod;
import edu.uy.um.wtf.entities.Snack;
import edu.uy.um.wtf.entities.Ticket;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/session")
public class SessionController {

    @PostMapping("/addPeymentMethod")
    public String addPeymentMethod(String key, PaymentMethod paymentMethod, HttpSession session) {
        if (paymentMethod == null) {
            return "redirect:/peymentMethods";
        }
        session.setAttribute(key, paymentMethod);
        return "redirect:/peymentMethods";
    }

    @PostMapping("/deletePeymentMethod")
    public String deletePeymentMethod(String key, HttpSession session) {
        session.removeAttribute(key);
        return "redirect:/peymentMethods";
    }

    @PostMapping("/addSnack")
    public String addSnack(String key, Snack snack, HttpSession session) {

        List<Snack> snacks = (List<Snack>) session.getAttribute(key);
        if (snacks == null) {
            snacks = new ArrayList<>();
        }
        snacks.add(snack);
        session.setAttribute(key, snacks);

        return "redirect:/snacks";
    }

    @PostMapping("/deleteSnack")
    public String deleteSnack(String key, HttpSession session) {

        List<Snack> snacks;
        try {
            snacks = (List<Snack>) session.getAttribute(key);
            session.setAttribute(key, snacks);
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
    public String addTicket(String key, Ticket ticket, HttpSession session) {

        List<Ticket> tickets = (List<Ticket>) session.getAttribute(key);
        if (tickets == null) {
            tickets = new ArrayList<>();
        }
        tickets.add(ticket);
        session.setAttribute(key, tickets);

        return "redirect:/tickets";
    }

    @PostMapping("/deleteTicket")
    public String deleteTicket(String key, HttpSession session) {

        List<Ticket> tickets;
        try {
            tickets = (List<Ticket>) session.getAttribute(key);
            session.setAttribute(key, tickets);
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
    public String addMovie(String key, Movie movie, HttpSession session) {

        List<Movie> movies = (List<Movie>) session.getAttribute(key);
        if (movies == null) {
            movies = new ArrayList<>();
        }
        movies.add(movie);
        session.setAttribute(key, movies);

        return "redirect:/movies";
    }

    @PostMapping("/deleteMovie")
    public String deleteMovie(String key, HttpSession session) {

        List<Movie> movies;
        try {
            movies = (List<Movie>) session.getAttribute(key);
            session.setAttribute(key, movies);
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
    public String invalidate(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}
