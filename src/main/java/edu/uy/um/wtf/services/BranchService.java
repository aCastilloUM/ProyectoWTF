package edu.uy.um.wtf.services;

import edu.uy.um.wtf.entities.Branch;
import edu.uy.um.wtf.entities.Room;
import edu.uy.um.wtf.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public Branch addBranch(String name, String location, List<Room> rooms, Integer roomsNumber) {
        if (name == null || location == null) {
            return null;
        }
        if (name.trim().equals("") || location.trim().equals("")) {
            return null;
        }

        Branch branch = Branch.builder()
                .name(name)
                .location(location)
                .rooms(rooms)
                .rommsNumber(roomsNumber)
                .build();

        return branchRepository.save(branch);
    }

    public List<Branch> getAll() {
        return branchRepository.findAll();
    }

    public Branch findById(Long id) {
        if (id == null) {
            return null;
        }
        return branchRepository.findById(id).orElse(null);
    }

    public Branch findByName(String name) {
        if (name == null) {
            return null;
        }
        return branchRepository.findByName(name);
    }

    public Branch findByLocation(String location) {
        if (location == null) {
            return null;
        }
        return branchRepository.findByLocation(location);
    }

    public Branch updateBranch(Branch Branch){
        if (branchRepository.existsById(Branch.getId())) {
            return branchRepository.save(Branch);
        }
        return null;
    }

}
