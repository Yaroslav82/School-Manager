package com.hillel.multi.service;

import com.hillel.model.JwtRequest;
import com.hillel.model.UserDTO;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.configuration.exceptions.UserExistsException;
import com.hillel.multi.persistent.entities.Role;
import com.hillel.multi.persistent.entities.User;
import com.hillel.multi.persistent.repositories.UserRepository;
import com.hillel.multi.service.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    @Lazy // Add to prevent cycle SecurityConfig -> UserDetailsService
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    public UserDTO createNewUser(JwtRequest jwtRequest) {
        if (userRepository.getByUsername(jwtRequest.getUsername()).isPresent()) {
            throw new UserExistsException();
        }
        User user = new User();
        user.setUsername(jwtRequest.getUsername());
        user.setPassword(passwordEncoder.encode(jwtRequest.getPassword()));
        user.setRoles(Set.of(roleService.getStudentRole()));
        return UserMapper.INSTANCE.userToDto(userRepository.save(user));
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.getUsers();
        return UserMapper.INSTANCE.usersToDto(users);
    }

    public UserDTO updateUserRole(Integer id, String roleName) {
        Role role = roleService.getRoleByName(roleName);
        User user = getUserById(id);
        user.getRoles().clear();
        user.getRoles().add(role);
        return UserMapper.INSTANCE.userToDto(userRepository.save(user));
    }

    public void deleteUser(Integer id) {
        getUserById(id);
        userRepository.deleteById(Long.valueOf(id));
    }

    private User getUserById(Integer id) {
        return userRepository.getById(id).orElseThrow(NotFoundException::new);
    }
}
