package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IBrandController;
import com.khangse616.serverfashionrs.mappers.impl.BrandDTOMapper;
import com.khangse616.serverfashionrs.models.Brand;
import com.khangse616.serverfashionrs.models.dto.BrandDTO;
import com.khangse616.serverfashionrs.services.IBrandService;
import com.khangse616.serverfashionrs.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class BrandController implements IBrandController {
    @Autowired
    private IBrandService brandService;

    @Autowired
    private IProductService productService;

    @Override
    public List<BrandDTO> getAllBrand() {
        List<BrandDTO> brandDTOS = brandService.getAllBrand().stream().map(v -> new BrandDTOMapper().mapRow(v)).collect(Collectors.toList());
        int countProduct = productService.countProductByBrandIsNull();

        BrandDTO brandDTO = new BrandDTO(-1, "Không rõ", "https://developers.google.com/maps/documentation/streetview/images/error-image-generic.png?hl=vi", countProduct);
        brandDTOS.add(brandDTO);
        return brandDTOS;
    }

    @Override
    public List<BrandDTO> getBrandFilter(String search) {
        if(search.equals("") || search.isEmpty()){
            return getAllBrand();
        }else{
            return brandService.getBrandFilter(search).stream().map(v -> new BrandDTOMapper().mapRow(v)).collect(Collectors.toList());
        }
    }

    @Override
    public BrandDTO getBrandById(int id) {
        return new BrandDTOMapper().mapRow(brandService.getBrandById(id));
    }
}
