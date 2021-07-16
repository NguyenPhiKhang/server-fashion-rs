package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.FlashSaleProduct;
import com.khangse616.serverfashionrs.repositories.FlashSaleProductRepository;
import com.khangse616.serverfashionrs.services.IFlashSaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashSaleProductService implements IFlashSaleProductService {
    @Autowired
    private FlashSaleProductRepository flashSaleProductRepository;


    @Override
    public List<FlashSaleProduct> getListProductFlashSaleForMobile(int id, int page, int pageSize) {
        int pageNew = page < 1 ? 0 : (page - 1) * pageSize;
        return flashSaleProductRepository.getListProductFlashSaleByIdForMobile(id, pageNew, pageSize);
    }
}
