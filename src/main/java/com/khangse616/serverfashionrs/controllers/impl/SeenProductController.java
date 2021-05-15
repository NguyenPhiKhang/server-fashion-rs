package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.ISeenProductController;
import com.khangse616.serverfashionrs.mappers.impl.ProductItemDTOMapper;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;
import com.khangse616.serverfashionrs.services.ISeenProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class SeenProductController implements ISeenProductController {
    @Autowired
    private ISeenProductService seenProductService;

    @Autowired
    private IImageDataService imageDataService;

    @Override
    public String createOrUpdateSeenProduct(int userId, int productId) {
        seenProductService.createOrUpdateSeenProduct(userId, productId);
        return "Đã cập nhật";
    }

    @Override
    public List<ProductItemDTO> getListSeenProduct(int userId) {
        return seenProductService
                .getListSeenProduct(userId).stream().map(value-> new ProductItemDTOMapper().mapRow(value.getProduct(), imageDataService)).collect(Collectors.toList());
    }
}
