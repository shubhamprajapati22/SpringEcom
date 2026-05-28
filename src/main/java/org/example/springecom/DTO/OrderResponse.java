package org.example.springecom.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Integer id,
        LocalDateTime orderDate,
        String status,
        UsersResponse user,
        List<OrderItemResponse> orderItems
) {}
