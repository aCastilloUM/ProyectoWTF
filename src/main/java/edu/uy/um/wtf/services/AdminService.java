package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.Admin;
import edu.uy.um.wtf.exceptions.InvalidDataException;
import edu.uy.um.wtf.repository.AdminRepository;
import org.hibernate.loader.ast.internal.LoaderSelectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidAlgorithmParameterException;
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

    public Admin findByID(Long id){
        return adminRepository.findByID(id);
    }

    public List<Admin> getAll() {return adminRepository.findAll();
    }

    public Admin addAdmin(Long id, String firstName, String lastName, Date birthdate, int age) throws InvalidDataException{
        if (firstName == null || lastName == null || birthdate == null || age < 0 || age > 100){
            throw new InvalidDataException("Los datos no son correctos");
        }

        Admin newAdmin = Admin.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthdate)
                .age(age)
                .build();
        return adminRepository.save(newAdmin);
    }

    public Admin updateAdmin(Admin unAdmin) {
        if (adminRepository.existsById(unAdmin.getId())) {
            return adminRepository.save(unAdmin);
        }
        return null;
    }

    public boolean delete(Admin unAdmin) {
        if (unAdmin == null){
            return false;
        }
        adminRepository.deleteById(unAdmin.getId());
        return true;
    }

    public List<Admin> todosLosAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getById(Long id) {
        return adminRepository.findById(id);
    }

}

