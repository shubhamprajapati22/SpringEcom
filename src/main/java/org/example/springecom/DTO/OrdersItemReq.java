package org.example.springecom.DTO;

public record OrdersItemReq(
        Integer productId,
        Integer productQuantity,
        Integer price
        ) {}
