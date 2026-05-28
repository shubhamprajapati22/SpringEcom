package org.example.springecom.service;

import org.example.springecom.model.Users;
import org.example.springecom.repository.UserRepo;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    @NullMarked // it indicates that values cant be null
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        String role = user.getRole();

        if (role != null && role.startsWith("ROLE_")) {
            role = role.substring(5);
        }


        assert role != null;
        return User.builder().
                username(user.getEmail()).
                password(user.getPassword()).
                roles(role).
                build();
    }
}
