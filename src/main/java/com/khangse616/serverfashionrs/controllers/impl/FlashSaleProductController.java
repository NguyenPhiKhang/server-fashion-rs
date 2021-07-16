package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IFlashSaleProductController;
import com.khangse616.serverfashionrs.mappers.impl.FlashSaleCardDTOMapper;
import com.khangse616.serverfashionrs.models.dto.FlashSaleCardDTO;
import com.khangse616.serverfashionrs.services.IFlashSaleProductService;
import com.khangse616.serverfashionrs.services.IImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class FlashSaleProductController implements IFlashSaleProductController {
    @Autowired
    private IFlashSaleProductService flashSaleProductService;

    @Autowired
    private IImageDataService imageDataService;

    @Override
    public List<FlashSaleCardDTO> getListProductFlashSaleForMobile(int id, int page, int pageSize) {
        return flashSaleProductService.getListProductFlashSaleForMobile(id, page, pageSize).stream().map(v->new FlashSaleCardDTOMapper().mapRow(v, imageDataService)).collect(Collectors.toList());
    }
}
