package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.ProductUserKey;
import com.khangse616.serverfashionrs.models.SeenProduct;
import com.khangse616.serverfashionrs.repositories.SeenProductRepository;
import com.khangse616.serverfashionrs.services.ISeenProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class SeenProductService implements ISeenProductService {
    @Autowired
    private SeenProductRepository seenProductRepository;

    @Override
    public void CreateOrUpdateSeenProduct(int userId, int productId) {
        SeenProduct seenProduct = new SeenProduct();
        seenProduct.setCount(seenProduct.getCount()+1);
        seenProduct.setLastTime(new Timestamp(System.currentTimeMillis()));
        seenProduct.setId(new ProductUserKey(productId, userId));

        seenProductRepository.save(seenProduct);
    }
}
