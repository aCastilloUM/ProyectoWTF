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
    private PaymentMethodService paymentMethodService;


    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/registerPost")
    public String registerUser(@RequestParam Long id, @RequestParam String username, @RequestParam String password,
                               @RequestParam String firstName, @RequestParam String lastName,
                               @RequestParam String email, @RequestParam("birthDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate,
                               @RequestParam(required = false) String cardNumber, @RequestParam(required = false) String cardHolderName,
                               @RequestParam(required = false) String expiryDate, @RequestParam(required = false) String cvv, Model model, RedirectAttributes redirectAttributes) {

        if (id == null || username == null || username.isEmpty() || password == null || password.isEmpty() ||
                firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() ||
                email == null || email.isEmpty() || birthDate == null) {
            model.addAttribute("error", "Todos los campos son requeridos");
            return "/register";
        }

        User newUser = User.builder()
                .id(id)
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .mail(email)
                .birthDate(birthDate)
                .build();

        userService.saveUser(newUser);

        if (cardNumber != null && !cardNumber.isEmpty() && cardHolderName != null && !cardHolderName.isEmpty() &&
                expiryDate != null && !expiryDate.isEmpty() && cvv != null && !cvv.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
            Date expirationDate;
            try {
                expirationDate = dateFormat.parse(expiryDate);
            } catch (ParseException e) {
                redirectAttributes.addFlashAttribute("error", "Fecha de expiración inválida");
                return "redirect:/register";
            }

            PeymentMethod newPaymentMethod = PeymentMethod.builder()
                    .cardNumber(Long.parseLong(cardNumber.replaceAll("\\s", ""))) // Remove spaces and parse as Long
                    .holderName(cardHolderName)
                    .expirationDate(expirationDate)
                    .cvv(cvv)
                    .user(newUser)
                    .build();

            paymentMethodService.savePaymentMethod(newPaymentMethod);
        }
        return "redirect:/logIn";
    }



}