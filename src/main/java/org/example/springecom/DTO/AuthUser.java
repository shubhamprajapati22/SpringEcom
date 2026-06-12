package org.example.springecom.DTO;

public record AuthUser(Integer id,
                       String name,
                       String email,
                       String password,
                       String role) {
}
