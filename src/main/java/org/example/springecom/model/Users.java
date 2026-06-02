package org.example.springecom.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password; //securing pwd will learn in spring security
    //Cascade help when we edit create or drop the row it will similarly affect the reference table
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // use already join created
    //@JsonManagedReference // use with parent
    private List<Orders> orders;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;
    private String role;

    public Users(int id){
        this.id = id;
    }
}
