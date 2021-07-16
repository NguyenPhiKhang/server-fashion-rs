package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.FlashSale;
import com.khangse616.serverfashionrs.models.dto.FlashSaleDTO;

import java.util.List;

public interface IFlashSaleService {
    List<FlashSale> getAllFlashSale();
    List<FlashSale> getFlashSaleTime();
}
