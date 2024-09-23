package edu.uy.um.wtf.controllers;

import edu.uy.um.wtf.entities.Admin;
import edu.uy.um.wtf.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminRestController {

    @Autowired
    private AdminService adminService;
    @GetMapping("/all")
    public ResponseEntity<List<Admin>> allAdmins() {
        List<Admin> admins = adminService.todosLosAdmins();
        return ResponseEntity.ok(admins);
    }

}
