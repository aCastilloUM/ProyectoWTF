package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void saveUser(User user) {
        userRepository.save(user);
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

    public User addUser(Long id, String firstName, String lastName, Date birthDate, String mail, String userName, String password){
        if (firstName == null || lastName == null || birthDate == null || mail == null || userName == null || password == null) {
            return null;
        }

        User newUser = User.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .mail(mail)
                .userName(userName)
                .password(password)
                .build();

        return userRepository.save(newUser);
    }

    public User authenticate(String userName, String password) {
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        return null;
    }

}
