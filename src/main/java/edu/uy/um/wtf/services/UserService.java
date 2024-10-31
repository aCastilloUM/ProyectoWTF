package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return Optional.empty();
        }
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByMail(email);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return Optional.empty();
        }
        return userRepository.findByUsername(username);
    }

    public User addUser(Long id, String firstName, String lastName, Date birthDate, String mail, String username, String password){
        if (firstName == null || lastName == null || birthDate == null || mail == null || username == null || password == null) {
            return null;
        }

        User newUser = User.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .mail(mail)
                .username(username)
                .password(password)
                .build();

        return userRepository.save(newUser);
    }

}
