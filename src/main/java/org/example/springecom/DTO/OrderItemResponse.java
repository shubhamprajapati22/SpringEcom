package org.example.springecom.DTO;

public record OrderItemResponse(
        Integer id,
        Integer productId,
        String productName,
        Integer quantity,
        Integer price
) {}
