package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.User;
import edu.uy.um.wtf.exceptions.InvalidDataException;
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
        userRepository.save(user);  // Llamada al repositorio para guardar el usuario
    }

    public User findByEmail(String email) {
        return userRepository.findByMail(email);  // Para buscar usuarios por correo electrónico
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User addUser(Long id, String firstName, String lastName, Date birthDate, int age, String mail) throws InvalidDataException {
        if (firstName == null || lastName == null || birthDate == null || age <= 0 || mail == null){
            throw new InvalidDataException("Los datos no son correctos");
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
}
