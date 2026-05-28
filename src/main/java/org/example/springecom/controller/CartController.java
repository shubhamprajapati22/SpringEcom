package org.example.springecom.controller;

import org.example.springecom.DTO.AddCartReq;
import org.example.springecom.model.Cart;
import org.example.springecom.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/user/cart")
//@CrossOrigin(origins = "http://localhost:5173") setup in securityConfig
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("add")
    public ResponseEntity<?> addOrUpdateToCart(@RequestBody AddCartReq product, Authentication auth){
        String email = (String) auth.getDetails();
        try {
            cartService.addOrUpdateProduct(product, email);
            return new ResponseEntity<>("add to cart", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getCart(Authentication auth){

        String email = (String) auth.getDetails();
        Cart cart;
        try {
            cart = cartService.getCart(email);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        if(cart != null)
            return new ResponseEntity<>(cart.getCartItems(), HttpStatus.OK);

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/item/{productId}")
    public ResponseEntity<?> removeItem(@PathVariable int productId, Authentication auth){
        cartService.removeItem(productId, (String) auth.getDetails());
        return new ResponseEntity<>("remove item", HttpStatus.OK);
    }

}
