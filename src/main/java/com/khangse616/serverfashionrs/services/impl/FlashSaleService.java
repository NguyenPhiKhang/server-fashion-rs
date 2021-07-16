package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.FlashSale;
import com.khangse616.serverfashionrs.repositories.FlashSaleRepository;
import com.khangse616.serverfashionrs.services.IFlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashSaleService implements IFlashSaleService {

    @Autowired
    private FlashSaleRepository flashSaleRepository;

    @Override
    public List<FlashSale> getAllFlashSale() {
        return flashSaleRepository.findAll();
    }
}
