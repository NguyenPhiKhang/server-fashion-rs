package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Product;

public interface IProductService {
    Product findProductById(int id);
}
