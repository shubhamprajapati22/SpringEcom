package org.example.springecom.service;

import jakarta.transaction.Transactional;
import org.example.springecom.DTO.AddCartReq;
import org.example.springecom.model.Cart;
import org.example.springecom.model.CartItem;
import org.example.springecom.model.Product;
import org.example.springecom.model.Users;
import org.example.springecom.repository.CartItemRepo;
import org.example.springecom.repository.CartRepo;
import org.example.springecom.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepo cartRepo;
    @Autowired
    UserService userService;
    @Autowired
    CartItemRepo itemRepo;
    @Autowired
    ProductRepo productRepo;

    public Cart createCart(String email){
        Users user = userService.getUserByEmail(email);
        Cart cart = new Cart(user);
        user.setCart(cart);
        return cartRepo.save(cart);
    }

    public Cart getCart(String email) {

        return cartRepo.findByUserEmail(email).orElseGet(() -> createCart(email));

    }

    @Transactional
    public void addOrUpdateProduct(AddCartReq request, String email) {
        Product product = productRepo.findById(request.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (!Boolean.TRUE.equals(product.getProductAvailable()) || product.getProductQuantity() == null || product.getProductQuantity() <= 0) {
            throw new IllegalArgumentException("Product is not available");
        }

        Cart cart = getCart(email);

        List<CartItem> items = cart.getCartItems();
        for(CartItem i : items){
            System.out.println(i.getProduct().getName());
            if(i.getProduct().getId().equals(product.getId())) {
                System.out.println("product matched");
                int newQuantity = i.getQuantity() + request.quantity();
                validateAvailableQuantity(product, newQuantity);
                i.setQuantity(newQuantity);
                itemRepo.save(i);
                return;
            }
        }
        validateAvailableQuantity(product, request.quantity());
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setCart(cart);
        item.setQuantity(request.quantity());
        itemRepo.save(item);
        cart.addItem(item);
        cartRepo.save(cart);

    }

    private void validateAvailableQuantity(Product product, int requestedQuantity) {
        if (requestedQuantity > product.getProductQuantity()) {
            throw new IllegalArgumentException(
                    "Only " + product.getProductQuantity() + " item(s) available for " + product.getName()
            );
        }
    }
    @Transactional // use when DELETE UPDATE INSERT QUERY MANUALLY RUN
    public void emptyCart(Cart cart) {

        itemRepo.deleteByCartId(cart.getId());


    }
    @Transactional
    public void removeItem(int productId, String email){
        System.out.println("delete called " + email);
        Cart cart = cartRepo.findByUserEmail(email).orElseThrow(()-> new UsernameNotFoundException("cart not found"));
        System.out.println(cart.getId() + " " + productId);
        List<CartItem> items = cart.getCartItems();
        for(CartItem i : items){
            System.out.println(i.getProduct().getName());
            if(i.getProduct().getId().equals(productId)){
                System.out.println("got the item");
                itemRepo.deleteByCartIdAndProductId(cart.getId(),productId);
            }
        }
    }

}
