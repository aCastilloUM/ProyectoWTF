package edu.uy.um.wtf.repository;

import edu.uy.um.wtf.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Branch findByLocation(String location);

    Branch findByName(String name);
}
