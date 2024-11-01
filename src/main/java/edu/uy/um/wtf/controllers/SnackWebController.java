package edu.uy.um.wtf.controllers;


import edu.uy.um.wtf.entities.Snack;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
            Snack snack = snackService.findById(id);
            model.addAttribute("snack", snack);
            return "snacks/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Id not found");
            return "error";
        }
    }

    @PostMapping("/add")
    public String addSnack(@RequestParam String name, @RequestParam double price, @RequestParam String type, Model model) {
        try {
            Snack snack = snackService.addSnack(name, price, type);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al agregar el snack");
        }
        return "snacksAdmin";
    }

    @GetMapping("/update{Id}{Name}{Price}{Stock}")
    public String updateSnack(@PathVariable("Id") Long id, @PathVariable("Name") String name, @PathVariable("Price") double price, @PathVariable("Type") String type, Model model) {
        try {
            Snack snack = snackService.findById(id);
            snack.setName(name);
            snack.setPrice(price);
            snack.setType(type);
            snackService.updateSnack(snack);
            model.addAttribute("snack", snack);
            return "snacks/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Error updating snack");
            return "error";
        }
    }

    @PostMapping("/delete")
    public String deleteSnack(@RequestParam Long id, Model model) {
        try {
            Snack snack = snackService.findById(id);
            snackService.deleteSnack(snack);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al eliminar el snack");
        }
        return "snacksAdmin";
    }




}
