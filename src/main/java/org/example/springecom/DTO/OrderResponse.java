package org.example.springecom.DTO;

import org.example.springecom.model.Payment;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Integer id,
        LocalDateTime orderDate,
        String status,
        PaymentResponse payment,
        List<OrderItemResponse> orderItems
) {}
