package org.example.springecom.controller;

import org.example.springecom.DTO.UsersResponse;
import org.example.springecom.model.Users;
import org.example.springecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getUser(Authentication auth){
        UsersResponse usersResponse = userService.getOnlyUserByEmail((String) auth.getDetails());
        return ResponseEntity.ok(usersResponse);
    }
    @PutMapping("updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody Users pwd, Authentication auth){
        Users user = userService.getUserByEmail((String) auth.getDetails());
        user.setPassword(pwd.getPassword());
        userService.addUser(user);
        return ResponseEntity.ok("Password updated");
    }
}
