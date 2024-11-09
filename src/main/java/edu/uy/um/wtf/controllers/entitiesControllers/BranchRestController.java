package edu.uy.um.wtf.controllers.entitiesControllers;

import edu.uy.um.wtf.entities.Branch;
import edu.uy.um.wtf.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branch")
public class BranchRestController {


    @Autowired
    private BranchService branchService;

    @GetMapping("/bymovie/{movieid}")
    public ResponseEntity<List<Branch>> branchByMovie(@PathVariable("movieid") long movieid)
    {
        return ResponseEntity.ok(branchService.findByMovie(movieid));
    }
}
