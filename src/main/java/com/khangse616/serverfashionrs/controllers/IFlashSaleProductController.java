package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.dto.FlashSaleCardDTO;
import com.khangse616.serverfashionrs.models.dto.InputAddProductFlashSale;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface IFlashSaleProductController {
    @GetMapping("/flashsale/{id}/products-mobile")
    List<FlashSaleCardDTO> getListProductFlashSaleForMobile(@PathVariable("id") int id,
                                                            @RequestParam(value = "p", defaultValue = "1") int page,
                                                            @RequestParam(value = "p_size", defaultValue = "10") int pageSize);

    @GetMapping("/flashsale/{id}/products-fs-admin")
    List<FlashSaleCardDTO> getListProductFlashSaleForAdmin(@PathVariable("id") int id,
                                                            @RequestParam(value = "s", defaultValue = "") String search,
                                                            @RequestParam(value = "p", defaultValue = "1") int page,
                                                            @RequestParam(value = "p_size", defaultValue = "10") int pageSize);

    @PostMapping("/flashsale/auto-create-product-flashsale")
    void autoCreateProductFlashSale();

    @GetMapping("/flashsale/{id}/count-product-fs-admin")
    int countListProductFlashSaleForAdmin(@PathVariable("id") int id,
                                          @RequestParam(value = "s", defaultValue = "") String search);

    @DeleteMapping("flashsale/{id}/remove-product-flashsale")
    String deleteFlashSaleProductById(@PathVariable("id")int id);

    @PostMapping("/flashsale/{id}/add-product-flashsale")
    String addProductFlashSale(@PathVariable("id") int id, @RequestBody InputAddProductFlashSale input);
}
