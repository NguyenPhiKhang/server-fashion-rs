package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.FlashSaleProduct;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface IFlashSaleProductService {
    List<FlashSaleProduct> getListProductFlashSaleForMobile(int id, int page, int pageSize);
    FlashSaleProduct getProductFlashSaleInProgress(int productId);
    void saveFlashSaveProduct(FlashSaleProduct flashSaleProduct);
}
