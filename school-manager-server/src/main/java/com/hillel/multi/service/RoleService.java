package com.hillel.multi.service;

import com.hillel.multi.persistent.entities.Role;
import com.hillel.multi.persistent.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getStudentRole() {
        return roleRepository.getByName("ROLE_STUDENT").get();
    }

    public Optional<Role> getRoleByName(String name) {
        return roleRepository.getByName(name);
    }
}
