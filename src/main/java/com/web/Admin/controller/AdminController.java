package com.web.Admin.controller;

import com.web.Admin.entity.Admin;
import com.web.Admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/v1/admin")
public class AdminController {


    @Autowired
   AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "addAdmin")
    public Admin createUser(@RequestBody Admin admin) {
        return adminService.saveUser(admin);
    }

    @GetMapping(value = "AdminDetailsGetById/{id}")
    public ResponseEntity<Admin> getUserById(@PathVariable Long id) {
        Optional<Admin> user = adminService.getUserById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "GetAllAdminDetails")
    public List<Admin> getAllUsers() {
        return adminService.getAllUsers();
    }

    @DeleteMapping("DeleteAdminDetailsById/{id}")
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }
}

