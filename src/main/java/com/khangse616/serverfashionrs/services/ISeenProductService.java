package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.SeenProduct;

import java.util.List;

public interface ISeenProductService {
    void createOrUpdateSeenProduct(int userId, int productId);
    List<SeenProduct> getListSeenProduct(int userId);
}
