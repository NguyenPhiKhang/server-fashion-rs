package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IBrandService {
    List<Brand> getAllBrand();
    List<Brand> getBrandFilter(String search);
    Brand getBrandById(int id);
}
