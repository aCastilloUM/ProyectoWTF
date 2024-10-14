package edu.uy.um.wtf.controllers;


import edu.uy.um.wtf.entities.FilmShow;
import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.entities.Room;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import edu.uy.um.wtf.services.FilmShowService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/filmshows")
public class FilmShowWebController {

    @Autowired
    private FilmShowService filmShowService;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<FilmShow> funciones = filmShowService.getAll();
        model.addAttribute("funciones", funciones);
        return "filmshows/list";
    }

    @GetMapping("/byId{Id}")
    public String findById(@PathVariable("Id") Long id, Model model) {
        try {
            FilmShow funcion = filmShowService.findById(id).orElseThrow(EntityNotFoundException::new);
            model.addAttribute("funcion", funcion);
            return "filmshows/detail";
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
            List<FilmShow> funciones = filmShowService.findByDate(date);
            model.addAttribute("funciones", funciones);
            return "filmshows/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Date not found");
            return "error";
        }
    }

    @GetMapping("/byMovie{Movie}")
    public String findByMovie(@PathVariable("Movie") Movie movie, Model model) {
        if (movie == null) {
            model.addAttribute("error", "Movie not found");
            return "error";
        }
        try {
            List<FilmShow> funciones = filmShowService.findByMovie(movie);
            model.addAttribute("funciones", funciones);
            return "filmshows/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Movie not found");
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
            List<FilmShow> funciones = filmShowService.findByRoom(room);
            model.addAttribute("funciones", funciones);
            return "filmshows/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Room not found");
            return "error";
        }
    }

    @GetMapping("/bySpecialEffects{SpecialEffects}")
    public String findBySpecialEffects(@PathVariable("SpecialEffects") String specialEffects, Model model) {
        if (specialEffects == null || specialEffects.isEmpty()) {
            model.addAttribute("error", "SpecialEffects not found");
            return "error";
        }
        try {
            List<FilmShow> funciones = filmShowService.findBySpecialEffects(specialEffects);
            model.addAttribute("funciones", funciones);
            return "filmshows/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "SpecialEffects not found");
            return "error";
        }
    }

    @GetMapping("/byLanguage{Language}")
    public String findByLanguage(@PathVariable("Language") String language, Model model) {
        if (language == null || language.isEmpty()) {
            model.addAttribute("error", "Language not found");
            return "error";
        }
        try {
            List<FilmShow> funciones = filmShowService.findByLanguage(language);
            model.addAttribute("funciones", funciones);
            return "filmshows/list";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Language not found");
            return "error";
        }
    }

    @GetMapping("/update{Id}{Date}{Language}{SpecialEffects}{Room}{Movie}")
    public String updateFunction(@PathVariable("Id") Long id, @PathVariable("Date") Date date, @PathVariable("Language") String language, @PathVariable("SpecialEffects") String specialEffects, @PathVariable("Room") Room room, @PathVariable("Movie") Movie movie, Model model) {
        try {
            FilmShow funcion = filmShowService.findById(id).orElseThrow(EntityNotFoundException::new);
            funcion.setDate(date);
            funcion.setLanguage(language);
            funcion.setSpecialEffects(specialEffects);
            funcion.setRoom(room);
            funcion.setMovie(movie);
            filmShowService.updateFunction(funcion);
            model.addAttribute("funcion", funcion);
            return "filmshows/detail";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Error updating function");
            return "error";
        }
    }

}
