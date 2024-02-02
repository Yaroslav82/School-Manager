package com.hillel.multi.presentation.controllers;

import com.hillel.api.AuthenticationManagerApi;
import com.hillel.model.JwtRequest;
import com.hillel.model.JwtResponse;
import com.hillel.model.UpdateUserRoleRequest;
import com.hillel.model.UserDTO;
import com.hillel.multi.service.UserDetailsServiceImpl;
import com.hillel.multi.service.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController implements AuthenticationManagerApi {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<JwtResponse> authUser(JwtRequest jwtRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        JwtResponse response = new JwtResponse();
        response.setToken(token);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UserDTO> createNewUser(JwtRequest jwtRequest) {
        UserDTO body = userService.createNewUser(jwtRequest);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> body = userService.getUsers();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> updateUserRole(Integer id, UpdateUserRoleRequest updateUserRoleRequest) {
        UserDTO body = userService.updateUserRole(id, updateUserRoleRequest.getRole());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
