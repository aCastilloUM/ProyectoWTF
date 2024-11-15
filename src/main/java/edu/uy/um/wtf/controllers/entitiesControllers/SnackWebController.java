package edu.uy.um.wtf.controllers.entitiesControllers;


import edu.uy.um.wtf.entities.Bill;
import edu.uy.um.wtf.entities.Movie;
import edu.uy.um.wtf.entities.Snack;
import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.services.BillService;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private BillService billService;
    @GetMapping("/snack")
    public String showSnackPage(HttpSession session, Model model) {
        // Verificamos si ya hay un user en la sesión
        User user = (User) session.getAttribute("user");

        if (user != null) {
            // Obtenemos todos los snacks disponibles
            List<Snack> Combos = snackService.getCombos();
            List<Snack> Pops = snackService.getSnacks();
            List<Snack> Bebidas = snackService.getDrinks();

            model.addAttribute("Combos", Combos);
            model.addAttribute("Pops", Pops);
            model.addAttribute("Bebidas", Bebidas);

            // Se asume que hay un usuario en la sesión, lo pasamos al modelo
            model.addAttribute("user", user);

            return "snacks";  // Nombre de la vista que se va a renderizar (asegúrate de que sea el correcto)
        } else {
            // Si no hay snack en la sesión, redirigimos al login
            return "redirect:/logIn";
        }
    }

    // Ejemplo en un controlador Spring Boot





    @GetMapping("/all")
    public String getAll(Model model) {
        List<Snack> snacks = snackService.getAll();
        model.addAttribute("snacks", snacks);
        return "snacks/list";
    }

    @GetMapping("/byName/{Name}")
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

    @GetMapping("/byId/{Id}")
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

    @PostMapping("/purchase")
    public String purchaseSnacks(@RequestParam(required = false) Long comboId, @RequestParam(required = false) int comboQty,
                                 @RequestParam(required = false) Long snackPopId, @RequestParam(required = false) int snackPopQty,
                                 @RequestParam(required = false) Long drinkId, @RequestParam(required = false) int drinkQty,
                                 HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/logIn";
        }

        Bill bill = billService.newBill(user);

        try {
            if (comboId != null && comboQty > 0) {
                Snack combo = snackService.findById(comboId);
                for (int i = 0; i < comboQty; i++) {
                    billService.addSnack(bill, combo, 1);
                }
            }

            if (snackPopId != null && snackPopQty > 0) {
                Snack snackPop = snackService.findById(snackPopId);
                for (int i = 0; i < snackPopQty; i++) {
                    billService.addSnack(bill, snackPop, 1);
                }
            }

            if (drinkId != null && drinkQty > 0) {
                Snack drink = snackService.findById(drinkId);
                for (int i = 0; i < drinkQty; i++) {
                    billService.addSnack(bill, drink, 1);
                }
            }
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Error al encontrar algún item.");
            return "error";
        }

        model.addAttribute("bill", bill);
        return "redirect:/users/PaymentMethodPost";
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
