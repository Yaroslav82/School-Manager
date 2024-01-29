package com.hillel.multi.service;

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

    public UserDTO createNewUser(UserDTO userDto) {
        if (userRepository.getByUsername(userDto.getUsername()).isPresent()) {
            throw new UserExistsException();
        }
        User user = UserMapper.INSTANCE.dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Set.of(roleService.getStudentRole()));
        return UserMapper.INSTANCE.userToDto(userRepository.save(user));
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.getUsers();
        return UserMapper.INSTANCE.usersToDto(users);
    }

    public UserDTO updateUserRole(Integer id, String roleName) {
        Role role = roleService.getRoleByName(roleName).orElseThrow(
                () -> new NotFoundException(String.format("Role %s is not found", roleName))
        );
        User user = getUserById(id);
        user.setRoles(Set.of(role));
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
