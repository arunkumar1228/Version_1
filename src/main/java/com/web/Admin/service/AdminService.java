package com.web.Admin.service;


import com.web.Admin.entity.Admin;
import com.web.Admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public Admin saveUser(Admin admin) {
        return adminRepository.save(admin);
    }

    public Optional<Admin> getUserById(Long id) {
        return adminRepository.findById(id);
    }

    public List<Admin> getAllUsers() {
        return adminRepository.findAll();
    }

    public void deleteUser(Long id) {
        adminRepository.deleteById(id);
    }
}
