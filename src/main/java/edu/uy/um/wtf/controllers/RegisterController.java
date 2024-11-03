package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.entities.PaymentMethod;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Optional;

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
    public String registerPaymentMethod(@RequestParam Long cardNumber, @RequestParam String cardHolder,
                                        @RequestParam String expirationDate, @RequestParam Integer cvv, RedirectAttributes redirectAttributes) {
        if (cardNumber == null || cardHolder == null || cardHolder.isEmpty() || expirationDate == null || expirationDate.isEmpty() || cvv == null) {
            redirectAttributes.addFlashAttribute("error", "Todos los campos son requeridos");
            return "redirect:/paymentMethod";
        }

        PaymentMethod newPaymentMethod = PaymentMethod.builder()
                .cardNumber(cardNumber)
                .holderName(cardHolder)
                .expirationDate(new Date(expirationDate))
                .cvv(cvv)
                .build();

        paymentMethodService.savePaymentMethod(newPaymentMethod);
        return "redirect:/logIn";
    }
}