package edu.uy.um.wtf.controllers;


import edu.uy.um.wtf.entities.Snack;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.uy.um.wtf.services.SnackService;
import org.springframework.ui.Model;


import java.util.List;

@Controller
@RequestMapping("/snacks")
public class SnackWebController {

    @Autowired
    private SnackService snackService;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Snack> snacks = snackService.getAll();
        model.addAttribute("snacks", snacks);
        return "snacks/list";
    }

    @GetMapping("/byName{Name}")
    public String findByName(@PathVariable("Name") String name, Model model) {
        try {
            Snack snack = snackService.findByName(name).orElseThrow(EntityNotFoundException::new);
            model.addAttribute("snack", snack);
            return "snacks/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Name not found");
            return "error";
        }
    }

    @GetMapping("/byId{Id}")
    public String findById(@PathVariable("Id") Long id, Model model) {
        try {
            Snack snack = snackService.findById(id).orElseThrow(EntityNotFoundException::new);
            model.addAttribute("snack", snack);
            return "snacks/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Id not found");
            return "error";
        }
    }

    @GetMapping("/add{Name}{Price}{Stock}")
    public String addSnack(@PathVariable("Name") String name, @PathVariable("Price") double price, @PathVariable("Stock") int stock, Model model) {
        try {
            Snack snack = snackService.addSnack(name, price, stock);
            model.addAttribute("snack", snack);
            return "snacks/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Error adding snack");
            return "error";
        }
    }

    @GetMapping("/update{Id}{Name}{Price}{Stock}")
    public String updateSnack(@PathVariable("Id") Long id, @PathVariable("Name") String name, @PathVariable("Price") double price, @PathVariable("Stock") int stock, Model model) {
        try {
            Snack snack = snackService.findById(id).orElseThrow(EntityNotFoundException::new);
            snack.setName(name);
            snack.setPrice(price);
            snack.setStock(stock);
            snackService.updateSnack(snack);
            model.addAttribute("snack", snack);
            return "snacks/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Error updating snack");
            return "error";
        }
    }

    @GetMapping("/delete{Id}")
    public String deleteSnack(@PathVariable("Id") Long id, Model model) {
        try {
            Snack snack = snackService.findById(id).orElseThrow(EntityNotFoundException::new);
            snackService.deleteSnack(snack);
            model.addAttribute("snack", snack);
            return "snacks/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Error deleting snack");
            return "error";
        }
    }




}
