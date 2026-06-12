package org.example.springecom.DTO;

public record PaymentResponse(Integer id,
                              String paymentType,
                              String paymentStaus) {
}
