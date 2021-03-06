package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IFlashSaleProductController;
import com.khangse616.serverfashionrs.controllers.IProductController;
import com.khangse616.serverfashionrs.mappers.impl.FlashSaleCardDTOMapper;
import com.khangse616.serverfashionrs.models.FlashSale;
import com.khangse616.serverfashionrs.models.FlashSaleProduct;
import com.khangse616.serverfashionrs.models.dto.FlashSaleCardDTO;
import com.khangse616.serverfashionrs.models.dto.InputAddProductFlashSale;
import com.khangse616.serverfashionrs.models.dto.ProductDetailDTO;
import com.khangse616.serverfashionrs.repositories.FlashSaleProductRepository;
import com.khangse616.serverfashionrs.repositories.FlashSaleRepository;
import com.khangse616.serverfashionrs.repositories.ProductRepository;
import com.khangse616.serverfashionrs.services.IFlashSaleProductService;
import com.khangse616.serverfashionrs.services.IImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class FlashSaleProductController implements IFlashSaleProductController {
    @Autowired
    private IFlashSaleProductService flashSaleProductService;

    @Autowired
    private FlashSaleProductRepository flashSaleProductRepository;

    @Autowired
    private IImageDataService imageDataService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IProductController productController;

    @Autowired
    private FlashSaleRepository flashSaleRepository;

    @Override
    public List<FlashSaleCardDTO> getListProductFlashSaleForMobile(int id, int page, int pageSize) {
        return flashSaleProductService.getListProductFlashSaleForMobile(id, page, pageSize).stream().map(v->new FlashSaleCardDTOMapper().mapRow(v, imageDataService)).collect(Collectors.toList());
    }

    @Override
    public List<FlashSaleCardDTO> getListProductFlashSaleForAdmin(int id, String search, int page, int pageSize) {
        return flashSaleProductService.getListProductFlashSaleForAdmin(id, search, page, pageSize).stream().map(v->new FlashSaleCardDTOMapper().mapRow(v, imageDataService)).collect(Collectors.toList());
    }

    @Override
    public void autoCreateProductFlashSale() {
        List<Integer> listId = productRepository.getListIdProductRandom();
        Random rd = new Random();
        for(int i =16;i<18;i++){
            int n_rd = 18 + rd.nextInt(25);
            for (int j = 0; j<n_rd;j++){
                FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
                ProductDetailDTO productDetail = productController.getProductById(listId.get(0), 0).getBody();

                int quantity = (int)Math.ceil(productDetail.getTotalQuantity()/2);
                int discount = (int) (productDetail.getPromotionPercent()+rd.nextInt(20));

                flashSaleProductRepository.insertFlashSale(i, listId.get(0), discount, quantity);

                listId.remove(0);
            }
        }
    }

    @Override
    public int countListProductFlashSaleForAdmin(int id, String search) {
        return flashSaleProductService.countListProductFlashSaleForAdmin(id, search);
    }

    @Override
    public String deleteFlashSaleProductById(int id) {
        flashSaleProductRepository.deleteById(id);
        return "Xo?? th??nh c??ng";
    }

    @Override
    public String addProductFlashSale(int id, InputAddProductFlashSale input) {
        for(int idP: input.getListProductId()){
            FlashSaleProduct flashSaleProduct = new FlashSaleProduct();
            flashSaleProduct.setQuantity(input.getQuantity());
            flashSaleProduct.setPercentDiscount(input.getPercent());
            flashSaleProduct.setProduct(productRepository.findById(idP));
            FlashSale flashSale =  flashSaleRepository.findById(id).orElse(null);
            if(flashSale!=null){
                flashSaleProduct.setFlashSale(flashSale);
            }

            flashSaleProductRepository.save(flashSaleProduct);
        }


        return "C???p nh???t th??nh c??ng!";
    }
}
