package org.example.springecom.service;

import org.example.springecom.DTO.AuthUser;
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
        AuthUser user = userRepo.findAuthUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
        String role = user.role();


        assert role != null;
        return User.builder().
                username(user.email()).
                password(user.password()).
                roles(role).
                build();
    }
}
