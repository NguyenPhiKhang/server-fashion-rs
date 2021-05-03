package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.repositories.ProductRepository;
import com.khangse616.serverfashionrs.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product findProductById(int id) {
        return productRepository.findByIdAndTypeId(id, "configurable");
    }

    @Override
    public List<Product> getProductsByCategories(List<Integer> idCategories) {
        return productRepository.findProductByCategories(idCategories);
    }
}
