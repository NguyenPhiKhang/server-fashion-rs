package com.khangse616.serverfashionrs.controllers;


import com.khangse616.serverfashionrs.models.FlashSale;
import com.khangse616.serverfashionrs.models.dto.FlashSaleDTO;
import com.khangse616.serverfashionrs.models.dto.FlashSaleForTableDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface IFlashSaleController {
    @GetMapping("/flashsale/get-all")
    List<FlashSaleDTO> getAllFlashSale();

    @GetMapping("/flashsale/get-for-mobile")
    List<FlashSaleDTO> getFlashSaleForMobile();

    @GetMapping("/flashsle/get-for-table")
    List<FlashSaleForTableDTO> getFlashSaleForTable(
            @RequestParam(value = "p", defaultValue = "1") int page,
            @RequestParam(value = "p_size", defaultValue = "5") int pageSize,
            @RequestParam(value = "status", defaultValue = "0") int status);

    @GetMapping("/flashsale/count-for-table")
    int countForTable(@RequestParam(value = "status", defaultValue = "0") int status);

}
