package edu.uy.um.wtf.controllers.entitiesControllers;


import edu.uy.um.wtf.entities.*;
import edu.uy.um.wtf.exceptions.EntityNotFoundException;
import edu.uy.um.wtf.services.BillService;
import edu.uy.um.wtf.services.PaymentMethodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import edu.uy.um.wtf.services.SnackService;
import org.springframework.ui.Model;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/snacks")
public class SnackWebController {

    @Autowired
    private SnackService snackService;
    @Autowired
    private PaymentMethodService paymentMethodService;
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

    @GetMapping("/all")
    public String getAll(Model model) {
        List<Snack> snacks = snackService.getAll();
        model.addAttribute("snacks", snacks);
        return "snacks/list";
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
                billService.addSnack(bill, combo, comboQty);
                session.setAttribute("ComboId", combo);
            }

            if (snackPopId != null && snackPopQty > 0) {
                Snack snackPop = snackService.findById(snackPopId);
                billService.addSnack(bill, snackPop, snackPopQty);
                session.setAttribute("SnackPop", snackPop);

            }

            if (drinkId != null && drinkQty > 0) {
                Snack drink = snackService.findById(drinkId);
                billService.addSnack(bill, drink, drinkQty);
                session.setAttribute("Drink", drink);

            }
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", "Error al encontrar algún item.");
            return "error";
        }

        model.addAttribute("bill", bill);
        List<PaymentMethod> paymentMethods = paymentMethodService.findByUser(user);
        if (paymentMethods.isEmpty()) {
            return "redirect:/paymentMethod/paymentMethod";
        }

        return "redirect:/bills/processPayment";
    }

    @PostMapping("/add")
    public String addSnack(@RequestParam String name, @RequestParam double price, @RequestParam String type, Model model) {
        try {
            Snack snack = snackService.addSnack(name, price, type);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al agregar el snack");
        }
        return "mainAdmin";
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
        return "mainAdmin";
    }

    @GetMapping("/info/{id}")
    public String getSnackInfo(@PathVariable Long id, Model model) {
        Optional<Snack> snack = Optional.ofNullable(snackService.findById(id));
        if (snack.isPresent()) {
            model.addAttribute("snack", snack.get());
        } else {
            model.addAttribute("error", "Snack not found");
            return "error";
        }
        return "snackInfo";
    }




}
