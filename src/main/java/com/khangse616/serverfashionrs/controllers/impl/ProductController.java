package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IProductController;
import com.khangse616.serverfashionrs.mappers.impl.ProductDetailDTOMapper;
import com.khangse616.serverfashionrs.mappers.impl.ProductItemDTOMapper;
import com.khangse616.serverfashionrs.models.Category;
import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.dto.ProductDetailDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.repositories.CategoryRepository;
import com.khangse616.serverfashionrs.repositories.ProductRepository;
import com.khangse616.serverfashionrs.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ProductController implements IProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IImageDataService imageDataService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IRatingService ratingService;

    @GetMapping("/product/{id}")
    @Override
    public ResponseEntity<ProductDetailDTO> getProductById(@PathVariable int id) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTOMapper().mapRow(productService.findProductById(id), imageDataService);
        return ResponseEntity.ok().body(productDetailDTO);
    }

    @GetMapping("/cat/{idCategory}/products")
    @Override
    public ResponseEntity<List<ProductItemDTO>> getProductsByCategory(@PathVariable("idCategory") int idCategory, @RequestParam(defaultValue = "popular",value = "filter") String filter, @RequestParam("p") int page) {
        Set<Category> list =  categoryService.findCategoriesByParentId(idCategory);

        List<Integer> listId = new ArrayList<>();
        getListIdCategory(list, listId);

        Pageable pageable = PageRequest.of(page-1, 20);

        List<ProductItemDTO> listProduct = new ArrayList<>();

        if(filter.equals("popular")) {
            Page<Product> pageProduct = productService.getProductsByCategoriesOrderByPopular(listId, pageable);
            listProduct = pageProduct.getContent().stream()
                    .map(value -> new ProductItemDTOMapper().mapRow(value, imageDataService)).collect(Collectors.toList());
        }else{
            if(filter.equals("new")){
                Page<Product> pageProduct = productService.getProductsByCategoriesOrderByNew(listId, pageable);
                listProduct = pageProduct.getContent().stream()
                        .map(value -> new ProductItemDTOMapper().mapRow(value, imageDataService)).collect(Collectors.toList());
            }
        }

        return  ResponseEntity.ok().body(listProduct);
    }

    @Override
    @PostMapping("/product/generate-id-rating-star")
    public ResponseEntity<String> generateIdRatingStarForProduct() {
        productService.generateIdRatingStar();

        return ResponseEntity.ok().body("done");
    }

    @Override
    @GetMapping("/recommend/top-rating/{userId}")
    public ResponseEntity<List<ProductItemDTO>> getProductTopRating(@PathVariable("userId") int userId, @RequestParam(value = "p", defaultValue = "1") int page) {
        long startTime = new Date().getTime();
        List<ProductItemDTO> list = new ArrayList<>();

//        if (userId == 0 || !(ratingService.checkUserIsRated(userId) > 0)) {
        if (userId == 0) {
            list = productService.productTopRating((page - 1) * 10).stream().map(value -> new ProductItemDTOMapper().mapRow(value, imageDataService)).collect(Collectors.toList());
        }
//        else {
//            if (!recommendRatingService.checkExistUser(userId)) {
//                recommend_product_for_user(userId);
//            }
//            list = productService.productRecommendForUser(userId).stream().map(value -> new ProductItemDTOMapper().mapRow(value)).collect(Collectors.toList());
//        }


        long endTime = new Date().getTime();
        long difference = endTime - startTime;
        System.out.println("Elapsed time in milliseconds: " + difference);
        return ResponseEntity.ok().body(list);
    }

    private void getListIdCategory(Set<Category> list, List<Integer> listId){
        if(list.size()==0)
            return;
        list.forEach(c->{
            Set<Category> listCAT = c.getCategories();
            if(listCAT.size()>0){
                getListIdCategory(listCAT, listId);
            }else{
                listId.add(c.getId());
            }
        });
    }
}
