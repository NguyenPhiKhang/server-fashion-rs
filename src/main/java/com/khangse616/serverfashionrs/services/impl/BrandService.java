package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Brand;
import com.khangse616.serverfashionrs.repositories.BrandRepository;
import com.khangse616.serverfashionrs.services.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> getBrandFilter(String search) {
        return brandRepository.findBrandFilter(search);
    }

    @Override
    public Brand getBrandById(int id) {
        return brandRepository.findById(id).get();
    }
}
