package org.example.springecom.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String paymentType;

    @Enumerated(EnumType.STRING)//this annotation allow to store data in string form
    //otherwise database store it in numeric form that can be issue
    private PaymentStatus status;

    public enum PaymentStatus {
        COMPLETED,
        PENDING,
        CANCELED
    }

    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders order;
}
