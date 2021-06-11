package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Shipping;
import com.khangse616.serverfashionrs.repositories.ShippingRepository;
import com.khangse616.serverfashionrs.services.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingService implements IShippingService {
    @Autowired
    private ShippingRepository shippingRepository;

    @Override
    public Shipping getShippingById(int id) {
        return shippingRepository.findById(id).orElse(null);
    }
}
