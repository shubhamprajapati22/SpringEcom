package org.example.springecom.service;

import org.example.springecom.DTO.UsersResponse;
import org.example.springecom.mapper.UsersMapper;
import org.example.springecom.model.Cart;
import org.example.springecom.model.Users;
import org.example.springecom.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder encoder;

    public Users addUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public List<Users> allUser() {
        return userRepo.findAll();


    }

    public Users getUser(int id){
        return userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public Users getUserByEmail(String email){
        return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public Cart getCartByEmail(String email) {
        return userRepo.findCartByEmail(email);
    }

    public Users updateUser(Users user){
        return userRepo.save(user);
    }

    public void removeUser(int id) {
        userRepo.deleteById(id);
    }
}
