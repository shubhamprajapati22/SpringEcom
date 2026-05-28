package org.example.springecom.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @CreationTimestamp // automatic set current date and time
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)//this annotation allow to store data in string form
    //otherwise database store it in numeric form that can be issue
    private OrderStatus status;
    public enum OrderStatus{
        PLACED,
        SHIPPED,
        DELIVERED,
        CANCELLED,
        pending
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    //@JsonBackReference // use with child
    private Users user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    //@JsonManagedReference
    private List<OrdersItem> ordersItems;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;



}

