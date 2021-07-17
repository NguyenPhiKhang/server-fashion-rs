package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.Utils.CategoryUtil;
import com.khangse616.serverfashionrs.Utils.ImageUtil;
import com.khangse616.serverfashionrs.Utils.RecommendSystemUtil;
import com.khangse616.serverfashionrs.mappers.impl.ProductItemDTOMapper;
import com.khangse616.serverfashionrs.models.Category;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.dto.InputCategoryDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.repositories.CategoryRepository;
import com.khangse616.serverfashionrs.services.ICategoryService;
import com.khangse616.serverfashionrs.services.IImageDataService;
import com.khangse616.serverfashionrs.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IImageDataService imageDataService;

    @Override
    public Set<Category> findCategoriesByParentId(int id) {
        return categoryRepository.findByParentId(id);
    }

    @Override
    public List<String> getAllNameCategories() {
        return categoryRepository.getAllName();
    }

    @Override
    public String autoSetIconCategory(int idCategory) {
        Category category = categoryRepository.findCategoryById(idCategory);

        List<Category> listCategory = new ArrayList<>();
        CategoryUtil.getListSubCategory(category, listCategory);

        Pageable pageable = PageRequest.of(0, 1);

        listCategory.forEach(c -> {
            Page<Product> pageProduct = productService.getProductByCategoryOrderPopular(c.getId(), pageable);
            Product product = pageProduct.getContent().get(0);
            ProductItemDTO productItemDTO = new ProductItemDTOMapper().mapRow(product, imageDataService);
            System.out.println("Category " + c.getId());
            System.out.println("Product " + productItemDTO.getId());
            c.setIcon(productItemDTO.getImgUrl());
            categoryRepository.save(c);
        });

        return "xong";
    }

    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public String addIconCategories(String[] arrIcon) {
        List<Category> categories = categoryRepository.getAllCategoryOrderById();

        for (int i = 0; i < categories.size(); i++) {
            categories.get(i).setIcon(arrIcon[i]);
//            categoryRepository.save(categories.get(i));
        }

        categoryRepository.saveAll(categories);

        return "Xong";
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllCategoriesOrderByLevel(int page, int pageSize, String search) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return categoryRepository.findAllCategoriesOrderByLevel(search, pageable).getContent();
    }

    @Override
    public int countCategories(String search) {
        return categoryRepository.countCategories(search);
    }

    @Override
    public List<Category> findCategoryByLevel(int level) {
        return categoryRepository.findByLevel(level);
    }

    @Override
    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    @Override
    public String getPathCategory(int id) {
        return categoryRepository.findPathCategory(id);
    }

    @Override
    public void createNewCategory(InputCategoryDTO inputCategory) {
        if (inputCategory.getId() == 0) {
            Category category = new Category();

            Random rd = new Random();

            int idCategory;
            do {
                idCategory = 100 + rd.nextInt(6000001);
            } while (categoryRepository.existsById(idCategory));

            category.setId(idCategory);
            category.setName(inputCategory.getName());
            ImageData imageData = saveImage(inputCategory.getIcon(), imageDataService);

            category.setIcon("http://localhost:8080/api/v1/image/" + imageData.getId());

            Category categoryParent = categoryRepository.findCategoryById(inputCategory.getParentId());

            category.setPath(categoryParent.getPath() + "/" + idCategory);
            category.setLevel(categoryParent.getLevel() + 1);
            category.setPathVarchar(categoryParent.getPathVarchar() + "/" + inputCategory.getPath());
            category.setParentCategory(categoryParent);

            categoryRepository.save(category);
        } else {
            Category category = categoryRepository.findCategoryById(inputCategory.getId());
            category.setName(inputCategory.getName());
            if (category.getId() != inputCategory.getParentId()) {
                Category categoryParent = categoryRepository.findCategoryById(inputCategory.getParentId());

                category.setPath(categoryParent.getPath() + "/" + inputCategory.getId());
                category.setLevel(categoryParent.getLevel() + 1);
                category.setPathVarchar(categoryParent.getPathVarchar() + "/" + inputCategory.getPath());
                category.setParentCategory(categoryParent);
            }


            if(inputCategory.getIcon()!=null){
                ImageData imageData = saveImage(inputCategory.getIcon(), imageDataService);

                category.setIcon("http://localhost:8080/api/v1/image/" + imageData.getId());

                Category categoryParent = categoryRepository.findCategoryById(inputCategory.getParentId());
            }

            categoryRepository.save(category);
        }
    }

    private ImageData saveImage(MultipartFile image, IImageDataService imageDataService) {
        String fileName = ImageUtil.fileName(imageDataService, image);
        try {
            MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, image.getContentType(), image.getInputStream());

            return imageDataService.storeImageData(multipartFile);
        } catch (IOException e) {
            return null;
        }
    }

//    @Override
//    public Set<String> recommendSearch(String keyword) {
//        Set<String> newWord = new HashSet<>();
//        getAllNameCategories().forEach(c-> {
//            newWord.addAll(Arrays.asList(c.split(" - ")));
//        });
//
//        return RecommendSystemUtil.calcCosineSimilaritySearch(keyword, new ArrayList<>(newWord)).entrySet().stream()
//                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
//                .limit(10)
//                .map(Map.Entry::getKey).collect(Collectors.toSet());
//    }
}
