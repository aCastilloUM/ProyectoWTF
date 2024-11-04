package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.entities.PeymentMethod;
import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.services.PaymentMethodService;
import edu.uy.um.wtf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password,
                               @RequestParam String firstName, @RequestParam String lastName,
                               @RequestParam String email, @RequestParam("birthDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate, Model model) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() ||
                firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() ||
                email == null || email.isEmpty() || birthDate == null) {
            model.addAttribute("error", "Todos los campos son requeridos");
            return "redirect:/register";
        }

        User newUser = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .firstName(firstName)
                .lastName(lastName)
                .mail(email)
                .birthDate(birthDate)
                .build();

        userService.saveUser(newUser);
        return "redirect:/logIn";
    }

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping("/paymentMethod")
    public String showPaymentMethodPage() {
        return "paymentMethod";
    }

    @PostMapping("/paymentMethodPost")
    public String registerPaymentMethod(@RequestParam String cardNumber, @RequestParam String cardHolderName,
                                        @RequestParam String expiryDate, @RequestParam String cvv, RedirectAttributes redirectAttributes) {
        if (cardNumber == null || cardHolderName == null || cardHolderName.isEmpty() || expiryDate == null || expiryDate.isEmpty() || cvv == null) {
            redirectAttributes.addFlashAttribute("error", "Todos los campos son requeridos");
            return "redirect:/paymentMethod";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
        Date expirationDate;
        try {
            expirationDate = dateFormat.parse(expiryDate);
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("error", "Fecha de expiración inválida");
            return "redirect:/paymentMethod";
        }

        PeymentMethod newPaymentMethod = PeymentMethod.builder()
                .cardNumber(Long.parseLong(cardNumber.replaceAll("\\s", ""))) // Remove spaces and parse as Long
                .holderName(cardHolderName)
                .expirationDate(expirationDate)
                .cvv(Integer.parseInt(cvv))
                .build();

        paymentMethodService.savePaymentMethod(newPaymentMethod);
        return "redirect:/logIn";
    }
}