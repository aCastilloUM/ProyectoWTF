package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.Snack;
import edu.uy.um.wtf.repository.SnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SnackService {

    @Autowired
    private SnackRepository snackRepository;

    public Snack addSnack(String name, double price, String type) {
        if (name == null || name.isEmpty() || type == null) {
            return null;
        }
        if (price < 0) {
            return null;
        }
        if (snackRepository.findByName(name).isPresent()) {
            return null;
        }
        if (name.trim().equals("")) {
            return null;
        }

        Snack snack = Snack.builder()
                .name(name)
                .price(price)
                .type(type)
                .build();

        return snackRepository.save(snack);
    }

    public List<Snack> getAll() {
        return snackRepository.findAll();
    }

    public Optional<Snack> findByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return snackRepository.findByName(name);
    }

    public Snack findById(Long id) {
        return snackRepository.findById(id).orElse(null);
    }

    public Snack updateSnack(Snack snack){
        if (snackRepository.existsById(snack.getId())){
            return snackRepository.save(snack);
        }
        return null;
    }

    public void deleteSnack(Snack snack) {
        snackRepository.delete(snack);
    }

    public List<Snack> getCombos() {
        return snackRepository.findByType("Combo");
    }

    public List<Snack> getSnacks() {
        return snackRepository.findByType("Pop");
    }

    public List<Snack> getDrinks() {
        return snackRepository.findByType("Bebida");
    }
}