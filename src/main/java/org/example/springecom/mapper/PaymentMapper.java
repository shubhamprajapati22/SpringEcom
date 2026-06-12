package org.example.springecom.mapper;

import org.example.springecom.DTO.PaymentResponse;
import org.example.springecom.model.Payment;

public class PaymentMapper {
    public static PaymentResponse paymentMapperDTO(Payment payment){
        return new PaymentResponse(payment.getId(),
                payment.getPaymentType(),
                payment.getStatus().name());
    }
}
