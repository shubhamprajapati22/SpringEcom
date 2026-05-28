package org.example.springecom.service;

import org.example.springecom.model.Payment;
import org.example.springecom.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    public Payment savePaymentDetails(Payment payment){
        return paymentRepo.save(payment);
    }
}
