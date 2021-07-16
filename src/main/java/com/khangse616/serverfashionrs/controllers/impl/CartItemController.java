package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.ICartItemController;
import com.khangse616.serverfashionrs.mappers.impl.CartItemDTOMapper;
import com.khangse616.serverfashionrs.models.FlashSaleProduct;
import com.khangse616.serverfashionrs.models.dto.CartItemDTO;
import com.khangse616.serverfashionrs.services.ICartItemService;
import com.khangse616.serverfashionrs.services.IFlashSaleProductService;
import com.khangse616.serverfashionrs.services.IImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CartItemController implements ICartItemController {
    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    private IImageDataService imageDataService;

    @Autowired
    private IFlashSaleProductService flashSaleProductService;

    @Override
    public List<CartItemDTO> getListProductInCart(int userId) {

        return cartItemService.getCartItemByUser(userId).stream().map(value->{
            CartItemDTO cartItemDTO = new CartItemDTOMapper().mapRow(value, imageDataService);
            FlashSaleProduct flashSaleProduct = flashSaleProductService.getProductFlashSaleInProgress(cartItemDTO.getProductId());

            if (flashSaleProduct != null)
                cartItemDTO.setDiscount(flashSaleProduct.getPercentDiscount());

            return cartItemDTO;
        }).collect(Collectors.toList());
    }
}
