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
import com.khangse616.serverfashionrs.services.ICategoryService;
import com.khangse616.serverfashionrs.services.IImageDataService;
import com.khangse616.serverfashionrs.services.IOptionProductVarcharService;
import com.khangse616.serverfashionrs.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/product/{id}")
    @Override
    public ResponseEntity<ProductDetailDTO> getProductById(@PathVariable int id) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTOMapper().mapRow(productService.findProductById(id), imageDataService);
        return ResponseEntity.ok().body(productDetailDTO);
    }

    @GetMapping("/cat/{idCategory}/products")
    @Override
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("idCategory") int idCategory, @RequestParam("p") int page) {
//        List<ProductItemDTO> list = productService.findProductByCategory(path, (page - 1) * 20).stream().map(value -> new ProductItemDTOMapper().mapRow(value)).collect(Collectors.toList());
//        return ResponseEntity.ok().body(list);
        Set<Category> list =  categoryService.findCategoriesByParentId(idCategory);

        List<Integer> listId = new ArrayList<>();
        getListIdCategory(list, listId);

//        List<ProductItemDTO> listProduct = productService.getProductsByCategories(listId).stream()
//                .map(value -> new ProductItemDTOMapper().mapRow(value)).collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page-1, 20);

        Page<Product> pageProduct = productService.getProductsByCategories(listId, pageable);

        List<Product> listProduct = pageProduct.getContent();

        return  ResponseEntity.ok().body(listProduct);
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
