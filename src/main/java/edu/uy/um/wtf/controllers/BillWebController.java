package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import org.springframework.ui.Model;
import edu.uy.um.wtf.entities.Bill;
import edu.uy.um.wtf.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/byClient{Client}")
    public String findByClient(@PathVariable("Client") User client, Model model) {
        try {
            List<Bill> bills = billService.findByClient(client);
            model.addAttribute("bills", bills);
            return "bills/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Client not found");
            return "error";
        }
    }

    @GetMapping("/byTotal{Total}")
    public String findByTotal(@PathVariable("Total") int total, Model model) {
        try {
            List<Bill> bills = billService.findByTotal(total);
            model.addAttribute("bills", bills);
            return "bills/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Total not found");
            return "error";
        }
    }

}
