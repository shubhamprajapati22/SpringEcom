package org.example.springecom.mapper;

import org.example.springecom.DTO.UsersResponse;
import org.example.springecom.model.Orders;
import org.example.springecom.model.Users;

import java.util.Collections;
import java.util.List;

public class UsersMapper {

    public static UsersResponse usersResDTO(Users user){

        return new UsersResponse(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole());
    }
}
