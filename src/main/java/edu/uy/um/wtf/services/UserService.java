package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User addUser(Long id, String firstName, String lastName, Date birthDate, String mail){
        if (firstName == null || lastName == null || birthDate == null || mail == null){
            return null;
        }

        User newUser = User.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .mail(mail)
                .build();

        return userRepository.save(newUser);
    }

    public boolean authenticate(String userName, String password) {
        Optional<User> user = userRepository.findByMail(userName);
        return user.isPresent();
    }
}
