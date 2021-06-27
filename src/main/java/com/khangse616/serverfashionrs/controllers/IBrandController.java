package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Brand;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000"})
public interface IBrandController {
    @GetMapping("/brand/get-all")
    List<Brand> getAllBrand();
}
