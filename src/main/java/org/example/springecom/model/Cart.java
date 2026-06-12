package org.example.springecom.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    private Users user;
    @JsonManagedReference
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL) //current table is parent
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart(Users user) {
        this.user = user;
    }

    public void addItem(CartItem item){
        this.cartItems.add(item);

    }
}
