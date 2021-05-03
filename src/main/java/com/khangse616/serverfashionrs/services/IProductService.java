package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Product;

import java.util.List;

public interface IProductService {
    Product findProductById(int id);
    List<Product> getProductsByCategories(List<Integer> idCategories);
}
