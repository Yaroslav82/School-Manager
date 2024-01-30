package com.hillel.multi.service;

import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.persistent.entities.Role;
import com.hillel.multi.persistent.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getStudentRole() {
        return roleRepository.getByName("ROLE_STUDENT").get();
    }

    public Role getRoleByName(String name) {
        return roleRepository.getByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Role %s is not found", name))
        );
    }
}
