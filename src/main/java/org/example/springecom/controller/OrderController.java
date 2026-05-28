package org.example.springecom.controller;

import org.example.springecom.DTO.OrderRequest;
import org.example.springecom.DTO.OrderResponse;
import org.example.springecom.mapper.OrderMapper;
import org.example.springecom.model.Orders;
import org.example.springecom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user/order") // all call with api comes here
//@CrossOrigin(origins = "http://localhost:5173") setup done in SecurityConfig
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("orders")
    public ResponseEntity<List<OrderResponse>> allOrder(Authentication auth){
        List<Orders> orders = orderService.getAllOrders((String) auth.getDetails());
        List<OrderResponse> orderResponses = new ArrayList<>();
        for(Orders o : orders) orderResponses.add(OrderMapper.orderResDTO(o));
        return ResponseEntity.ok(orderResponses);
    }

    @PostMapping("placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest, Authentication auth){

        System.out.println("Order placed");
        try {
            orderService.addOrder(orderRequest, (String) auth.getDetails());
            return ResponseEntity.ok("order successful");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
