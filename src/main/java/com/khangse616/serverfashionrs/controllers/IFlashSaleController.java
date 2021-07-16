package com.khangse616.serverfashionrs.controllers;


import com.khangse616.serverfashionrs.models.FlashSale;
import com.khangse616.serverfashionrs.models.dto.FlashSaleDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface IFlashSaleController {
    @GetMapping("/flashsale/get-all")
    List<FlashSaleDTO> getAllFlashSale();

    @GetMapping("/flashsale/get-for-mobile")
    List<FlashSaleDTO> getFlashSaleForMobile();
}
