package edu.uy.um.wtf.controllers.entitiesControllers;


import edu.uy.um.wtf.entities.Room;
import edu.uy.um.wtf.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/rooms")
public class RoomWebController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/all")
    public String getAll(Model model) {
        List<Room> rooms = roomService.getAll();
        model.addAttribute("rooms", rooms);
        return "rooms/list";
    }

    @PostMapping("/add")
    public String addRoom(@RequestParam String number, @RequestParam  int capacity, @RequestParam  int rows, @RequestParam  int columns, Model model) {
        Room room = roomService.addRoom(number, capacity, rows, columns);
        if (room == null) {
            model.addAttribute("error", "Room not added");
            return "error";
        }
        model.addAttribute("room", room);
        return "rooms/detail";
    }


}
