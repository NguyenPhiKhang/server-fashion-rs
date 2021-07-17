package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.Brand;
import com.khangse616.serverfashionrs.models.dto.BrandDTO;
import com.khangse616.serverfashionrs.models.dto.InputBrandDTO;
import com.khangse616.serverfashionrs.models.dto.InputCategoryDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface IBrandController {
    @GetMapping("/brand/get-all")
    List<BrandDTO> getAllBrand();

    @GetMapping("/brand/get-filter")
    List<BrandDTO> getBrandFilter(@RequestParam(value = "search", defaultValue = "") String search);

    @GetMapping("/brand/{id}")
    BrandDTO getBrandById(@PathVariable("id") int id);

    @RequestMapping(value = "/brand/create-new", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String createNewBrand(@ModelAttribute("input_brand") InputBrandDTO inputBrand);

    @DeleteMapping("/brand/{idBrand}/delete")
    ResponseEntity<ResponseMessage<Integer>> deleteBrandById(@PathVariable int idBrand);
}
