package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Payment;
import com.khangse616.serverfashionrs.repositories.PaymentRepository;
import com.khangse616.serverfashionrs.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment getPaymentById(int id) {
        return paymentRepository.findById(id).orElse(null);
    }
}
