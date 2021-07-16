package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.FlashSaleProduct;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public interface IFlashSaleProductService {
    List<FlashSaleProduct> getListProductFlashSaleForMobile(int id, int page, int pageSize);
}
