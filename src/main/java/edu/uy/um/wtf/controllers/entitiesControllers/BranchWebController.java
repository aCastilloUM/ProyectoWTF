package edu.uy.um.wtf.controllers.entitiesControllers;


import edu.uy.um.wtf.entities.Room;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import org.springframework.ui.Model; // Correct import
import edu.uy.um.wtf.entities.Branch;
import edu.uy.um.wtf.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/branches")
public class BranchWebController {

    @Autowired
    private BranchService branchService;

    @GetMapping("/all")
    public String getAll(Model model){
        List<Branch> branches = branchService.getAll();
        model.addAttribute("branches", branches);
        return "branches/list";
    }

    @PostMapping("/add")
    public String addBranch(@RequestParam String name, @RequestParam String location,
                            @RequestParam List<Room> rooms, @RequestParam Integer roomsNumber, @RequestParam Model model){
        Branch branch = branchService.addBranch(name, location, rooms, roomsNumber);
        if (branch == null) {
            model.addAttribute("error", "Branch not added");
            return "error";
        }
        model.addAttribute("sucursal", branch);
        return "branches/detail";
    }



}
