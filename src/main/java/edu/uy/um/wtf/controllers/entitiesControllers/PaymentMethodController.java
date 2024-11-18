package edu.uy.um.wtf.controllers.entitiesControllers;

import edu.uy.um.wtf.entities.Bill;
import edu.uy.um.wtf.entities.PeymentMethod;
import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.services.PaymentMethodService;
import edu.uy.um.wtf.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/paymentMethod")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private UserService userService;


    @GetMapping("/paymentMethod")
    public String showPaymentMethodPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/logIn";
        }

        List<PeymentMethod> paymentMethods = paymentMethodService.findByUser(user);
        model.addAttribute("paymentMethods", paymentMethods != null ? paymentMethods : List.of());
        model.addAttribute("newPaymentMethod", new PeymentMethod());
        return "paymentMethod";
    }

    @PostMapping("/paymentMethodPost")
    public String registerPaymentMethod(@ModelAttribute PeymentMethod newPaymentMethod, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/logIn";
        }

        if (newPaymentMethod.getCardNumber() != null && !newPaymentMethod.getCardNumber().toString().isEmpty()) {
            newPaymentMethod.setUser(user);
            paymentMethodService.savePaymentMethod(newPaymentMethod);
        }

        return "redirect:/bills/processPayment";
    }

    @GetMapping("/checkPaymentMethod")
    public String checkPaymentMethod(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/logIn";
        }

        List<PeymentMethod> paymentMethods = paymentMethodService.findByUser(user);
        if (paymentMethods.isEmpty()) {
            return "paymentMethod";
        }

        return "/showPaymentMethods";
    }

    //mostrar metodos de pagos agregados para seleccionar
    @GetMapping("/showPaymentMethods")
    public String showPaymentMethods(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/logIn";
        }

        List<PeymentMethod> paymentMethods = paymentMethodService.findByUser(user);
        model.addAttribute("paymentMethods", paymentMethods);
        return "paymentMethods";
    }
}
