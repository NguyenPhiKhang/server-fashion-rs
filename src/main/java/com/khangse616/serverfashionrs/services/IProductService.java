package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Product findProductById(int id);
    Page<Product> getProductsByCategories(List<Integer> idCategories, Pageable pageable);
}
