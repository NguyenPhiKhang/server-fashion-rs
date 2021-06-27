package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IBrandController;
import com.khangse616.serverfashionrs.models.Brand;
import com.khangse616.serverfashionrs.services.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BrandController implements IBrandController {
    @Autowired
    private IBrandService brandService;

    @Override
    public List<Brand> getAllBrand() {
        return brandService.getAllBrand();
    }
}
