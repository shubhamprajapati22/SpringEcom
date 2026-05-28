package org.example.springecom.service;

import org.example.springecom.DTO.OrderRequest;
import org.example.springecom.model.*;
import org.example.springecom.repository.OrderItemRepo;
import org.example.springecom.repository.OrderRepo;
import org.example.springecom.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    public ProductRepo productRepo;


    public List<Orders> getAllOrders(String email) {
        return orderRepo.findAllByUserEmail(email);
    }


    @Transactional
    public void addOrder(OrderRequest orderRequest, String email) throws Exception{
        Users user = userService.getUserByEmail(email);
        Cart cart = user.getCart();
        if (cart == null || cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            throw new Exception("Cart is empty");
        }
//tip - if cascade used in parent, update details in parent, child will automatically updated
        List<CartItem> cartItems = cart.getCartItems();
        Orders order = new Orders();
        Payment payment = new Payment();
        List<OrdersItem> ordersItems = new ArrayList<>();

        //adding all order values
        order.setStatus(Orders.OrderStatus.PLACED);
        order.setUser(user);
        order.setOrdersItems(ordersItems);
        order.setPayment(payment);

        //adding all payment value
        payment.setPaymentType(orderRequest.paymentType());
        if(orderRequest.paymentType().equals("cod"))
            payment.setStatus(Payment.PaymentStatus.PENDING);
        else
            payment.setStatus(Payment.PaymentStatus.COMPLETED);
        payment.setOrder(order);

        //adding all orderItem values
        for(CartItem item : cartItems){
            OrdersItem ordersItem = new OrdersItem();
            Product product = item.getProduct();
            ordersItem.setProduct(product);
            //update product available quantity
            product.setProductQuantity(product.getProductQuantity() - item.getQuantity());
            ordersItem.setQuantity(item.getQuantity());
            ordersItem.setPrice(item.getProduct().getPrice());
            ordersItem.setOrders(order);

            ordersItems.add(ordersItem);
            productRepo.save(product);
        }

        cartService.emptyCart(cart);

        orderRepo.save(order);


    }
}
