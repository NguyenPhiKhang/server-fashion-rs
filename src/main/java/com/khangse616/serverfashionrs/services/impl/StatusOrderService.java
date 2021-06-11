package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.StatusOrder;
import com.khangse616.serverfashionrs.repositories.StatusOrderRepository;
import com.khangse616.serverfashionrs.services.IStatusOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusOrderService implements IStatusOrderService {
    @Autowired
    private StatusOrderRepository statusOrderRepository;

    @Override
    public StatusOrder getStatusOrderById(int id) {
        return statusOrderRepository.findById(id).orElse(null);
    }
}
