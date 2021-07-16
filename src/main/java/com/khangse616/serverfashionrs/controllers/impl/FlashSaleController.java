package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IFlashSaleController;
import com.khangse616.serverfashionrs.models.FlashSale;
import com.khangse616.serverfashionrs.services.IFlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class FlashSaleController implements IFlashSaleController {
    @Autowired
    private IFlashSaleService flashSaleService;

    @Override
    public List<FlashSale> getAllFlashSale() {
        return flashSaleService.getAllFlashSale();
    }
}
