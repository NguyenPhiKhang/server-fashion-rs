package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.ProductUserKey;
import com.khangse616.serverfashionrs.models.SeenProduct;
import com.khangse616.serverfashionrs.repositories.SeenProductRepository;
import com.khangse616.serverfashionrs.services.IProductService;
import com.khangse616.serverfashionrs.services.ISeenProductService;
import com.khangse616.serverfashionrs.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SeenProductService implements ISeenProductService {
    @Autowired
    private SeenProductRepository seenProductRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @Override
    public void CreateOrUpdateSeenProduct(int userId, int productId) {

        if (seenProductRepository.existsSeenProductByIdProductIdAndIdUserId(productId, userId)) {
            SeenProduct seenProduct = seenProductRepository.findSeenProductByIdProductIdAndIdUserId(productId, userId);
            seenProduct.setCount(seenProduct.getCount() + 1);
            seenProduct.setLastTime(new Timestamp(System.currentTimeMillis()));

            seenProductRepository.save(seenProduct);
        } else {
            SeenProduct seenProduct = new SeenProduct();
            seenProduct.setCount(1);
            seenProduct.setLastTime(new Timestamp(System.currentTimeMillis()));
            seenProduct.setProduct(productService.findProductByIdVisibleTrue(productId));
            seenProduct.setUser(userService.getUserById(userId));
            seenProduct.setId(new ProductUserKey(productId, userId));

            seenProductRepository.save(seenProduct);
        }
    }

    @Override
    public List<SeenProduct> getListSeenProduct(int userId) {
        return seenProductRepository.findSeenProductByUserAndLastTime(userId);
    }
}
