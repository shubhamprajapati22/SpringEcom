package org.example.springecom.controller;

import org.example.springecom.model.Users;
import org.example.springecom.service.JwtService;
import org.example.springecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:5173")setup done in SecurityConfig
public class AuthController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Users user){
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())//give raw password
                );
                if(auth.isAuthenticated()) {
                    System.out.println("authenticated");
                    Users userFromDb = userService.getUserByEmail(user.getEmail());
                    Map<String, String> response = new HashMap<>();
                    response.put("token", jwtService.generateToken(user.getEmail(), userFromDb.getId()));
                    response.put("role", userFromDb.getRole());
                    response.put("name", userFromDb.getName());
                    return ResponseEntity.ok(response);
                }
                return ResponseEntity.notFound().build();

    }
    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody Users user){
        String rawPassword = user.getPassword();

        user.setRole("USER");

        user = userService.addUser(user);

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), rawPassword)
        );
        if(auth.isAuthenticated()){
            Users userFromDb = userService.getUserByEmail(user.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtService.generateToken(user.getEmail(), userFromDb.getId()));
            response.put("role", userFromDb.getRole());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.notFound().build();

    }

}
