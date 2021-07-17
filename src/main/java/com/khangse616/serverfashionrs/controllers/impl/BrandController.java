package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IBrandController;
import com.khangse616.serverfashionrs.mappers.impl.BrandDTOMapper;
import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.Brand;
import com.khangse616.serverfashionrs.models.dto.BrandDTO;
import com.khangse616.serverfashionrs.models.dto.InputBrandDTO;
import com.khangse616.serverfashionrs.repositories.BrandRepository;
import com.khangse616.serverfashionrs.services.IBrandService;
import com.khangse616.serverfashionrs.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private BrandRepository brandRepository;

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

    @Override
    public String createNewBrand(InputBrandDTO inputBrand) {
        brandService.createNewCategory(inputBrand);
        if(inputBrand.getId()==0)
            return "Tạo danh mục thành công!";
        else return "Cập nhật thành công!";
    }

    @Override
    public ResponseEntity<ResponseMessage<Integer>> deleteBrandById(int idBrand) {
        try{
            brandRepository.deleteById(idBrand);
            return ResponseEntity.ok().body(new ResponseMessage<>("Xoá thành công", 1));
        }catch (Exception ex){
            return ResponseEntity.ok().body(new ResponseMessage<>("Không xoá được", -1));
        }
    }
}
