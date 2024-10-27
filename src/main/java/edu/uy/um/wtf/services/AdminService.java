package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.Admin;
import edu.uy.um.wtf.exceptions.InvalidDataException;
import edu.uy.um.wtf.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public void saveAdmin(Admin admin){
        adminRepository.save(admin);
    }

    public Optional<Admin> findById(Long id){
        return adminRepository.findById(id);
    }

    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    public Admin addAdmin(Long id, String firstName, String lastName, Date birthdate) throws InvalidDataException {
        if (firstName == null || lastName == null || birthdate == null){
            throw new InvalidDataException("Los datos no son correctos");
        }

        Admin newAdmin = Admin.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthdate)
                .build();
        return adminRepository.save(newAdmin);
    }

    public Admin updateAdmin(Admin admin) {
        if (adminRepository.existsById(admin.getId())) {
            return adminRepository.save(admin);
        }
        return null;
    }

    public boolean delete(Admin admin) {
        if (admin == null){
            return false;
        }
        adminRepository.deleteById(admin.getId());
        return true;
    }

    public Optional<Admin> getById(Long id) {
        return adminRepository.findById(id);
    }

    public List<Object> findByFirstNameAndLastName(String firstName, String lastName) {
        return adminRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public boolean authenticate(String userName, String password) {
        Optional<Admin> user = adminRepository.findByMail(userName);
        return user.isPresent();
    }
}