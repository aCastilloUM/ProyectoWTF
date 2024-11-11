package edu.uy.um.wtf.controllers.entitiesControllers;


import edu.uy.um.wtf.entities.Branch;
import edu.uy.um.wtf.entities.FilmShow;
import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.entities.Room;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.services.BranchService;
import edu.uy.um.wtf.services.MovieService;
import edu.uy.um.wtf.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import edu.uy.um.wtf.services.FilmShowService;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/filmshows")
public class FilmShowWebController {

    @Autowired
    private FilmShowService filmShowService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private BranchService branchService;
    @Autowired
    private RoomService roomService;


    @GetMapping("/movie/{movieId}/filmshows")
    @ResponseBody
    public List<FilmShow> getFilmShowsForMovie(@PathVariable Long movieId, Model model) {
        // Lógica para obtener las funciones (FilmShows) de la película con movieId
        List<FilmShow> filmShows = filmShowService.getFilmShowsByMovieId(movieId);

        // Añade la lista de funciones al modelo
        model.addAttribute("filmShows", filmShows);

        // Devuelve un fragmento Thymeleaf que renderiza las funciones
        return filmShows;
    }


    @GetMapping("/all")
    public String getAll(Model model) {
        List<FilmShow> funciones = filmShowService.getAll();
        model.addAttribute("funciones", funciones);
        return "filmshows/list";
    }

    @GetMapping("/{Id}")
    public String findById(@PathVariable("Id") Long id, Model model) {
        try {
            FilmShow funcion = filmShowService.findById(id).orElseThrow(EntityNotFoundException::new);
            model.addAttribute("funcion", funcion);
            return "main";
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
           /* funcion.setRoom(room);
            funcion.setMovie(movie);*/
            filmShowService.updateFunction(funcion);
            model.addAttribute("funcion", funcion);
            return "adminAdmin";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Error updating function");
            return "error";
        }
    }

    @PostMapping("/add")
    public String addFunction(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam LocalTime time,
                              @RequestParam Long movieId, @RequestParam String branch,
                              @RequestParam Long room, @RequestParam String specialEffects,
                              @RequestParam String language, Model model) {

        try {
            Optional<Movie> movieOptional = movieService.findById(movieId);
            Movie movie1 = movieOptional.orElseThrow(() -> new EntityNotFoundException("Movie not found"));

            Branch branch1 = branchService.findByName(branch);
            if (branch1 == null) {
                throw new EntityNotFoundException("Branch not found");
            }

            Optional<Room> roomOptional = roomService.findById(room);
            Room room1 = roomOptional.orElseThrow(() -> new EntityNotFoundException("Room not found"));

            FilmShow funcion = filmShowService.addFunction(date, time, movie1, branch1, room1, specialEffects, language);
        } catch (EntityNotFoundException e) {
            System.out.println("EntityNotFoundException: " + e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "mainAdmin";
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            model.addAttribute("errorMessage", "Error al agregar la función");
            return "mainAdmin";
        }

        return "filmshowAdmin";
    }

    @PostMapping("/delete")
    public String deleteFunction(@RequestParam Long id, Model model) {
        try {
            filmShowService.deleteFunction(id);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al eliminar la funcion");
        }
        return "filmshowAdmin";
    }
}
