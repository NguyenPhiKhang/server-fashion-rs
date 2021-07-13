package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Brand;
import com.khangse616.serverfashionrs.models.dto.BrandDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000"})
public interface IBrandController {
    @GetMapping("/brand/get-all")
    List<BrandDTO> getAllBrand();

    @GetMapping("/brand/get-filter")
    List<BrandDTO> getBrandFilter(@RequestParam(value = "search", defaultValue = "") String search);

    @GetMapping("/brand/{id}")
    BrandDTO getBrandById(@PathVariable("id") int id);
}
