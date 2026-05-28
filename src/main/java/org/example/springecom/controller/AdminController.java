package org.example.springecom.controller;

import oracle.jdbc.proxy.annotation.Post;
import org.example.springecom.DTO.UsersResponse;
import org.example.springecom.mapper.UsersMapper;
import org.example.springecom.model.Users;
import org.example.springecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;


    @GetMapping("allUser")
    public ResponseEntity<?> getAllUser(){
        System.out.println("admin controller called");
        List<Users> users = userService.allUser();
        List<UsersResponse> usersResponses = new ArrayList<>();
        for(Users u : users){
            usersResponses.add(UsersMapper.usersResDTO(u));
        }
        return ResponseEntity.ok(usersResponses);

    }

    @PutMapping("user/{id}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable int id, @RequestBody Map<String, String> userRole){
        Users user = userService.getUser(id);
        user.setRole(userRole.get("role"));
        userService.updateUser(user);
        return ResponseEntity.ok("role updated");
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        userService.removeUser(id);
        return ResponseEntity.ok("deleted");
    }
}
