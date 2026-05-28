package org.example.springecom.mapper;

import org.example.springecom.DTO.OrderResponse;
import org.example.springecom.DTO.OrderItemResponse;
import org.example.springecom.model.Orders;
import org.example.springecom.model.OrdersItem;

import java.util.List;

public class OrderMapper {
    public static OrderResponse orderResDTO(Orders order){
        return new OrderResponse(order.getId(),
                order.getOrderDate(),
                order.getStatus().name(),
                UsersMapper.usersResDTO(order.getUser()),
                orderItemResDTO(order.getOrdersItems())
                );
    }

    private static List<OrderItemResponse> orderItemResDTO(List<OrdersItem> orderItems) {
        return orderItems.stream()
                .map(item -> new OrderItemResponse(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .toList();
    }
}
