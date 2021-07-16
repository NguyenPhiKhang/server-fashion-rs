package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.dto.FlashSaleCardDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface IFlashSaleProductController {
    @RequestMapping("/flashsale/{id}/products-mobile")
    List<FlashSaleCardDTO> getListProductFlashSaleForMobile(@PathVariable("id") int id, @RequestParam(value = "p", defaultValue = "1") int page, @RequestParam(value = "p_size", defaultValue = "10") int pageSize);
}
