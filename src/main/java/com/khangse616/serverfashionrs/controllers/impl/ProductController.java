package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.Utils.CategoryUtil;
import com.khangse616.serverfashionrs.controllers.IProductController;
import com.khangse616.serverfashionrs.controllers.ISeenProductController;
import com.khangse616.serverfashionrs.mappers.impl.AttributeOptionDTOMapper;
import com.khangse616.serverfashionrs.mappers.impl.ProductDetailDTOMapper;
import com.khangse616.serverfashionrs.mappers.impl.ProductItemDTOMapper;
import com.khangse616.serverfashionrs.mappers.impl.RatingRSDTOMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.*;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.AVGRatedProductDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.RatingRSDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.RecommendForUser;
import com.khangse616.serverfashionrs.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
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

    @Autowired
    private ICosineSimilarityService cosineSimilarityService;

    @Autowired
    private IRecommendRatingService recommendRatingService;

    @Autowired
    private ISeenProductController seenProductController;

    @Autowired
    private IFavoriteService favoriteService;

    @Autowired
    private IHistorySearchService historySearchService;

    @Autowired
    private IFlashSaleProductService flashSaleProductService;

    @Override
    public ResponseEntity<ProductDetailDTO> getProductById(int id, int userId) {
        Product product = productService.findProductByIdVisibleTrue(id);
        if (userId != 0) {
            seenProductController.createOrUpdateSeenProduct(userId, id);
            if (favoriteService.checkUserLikedProduct(userId, id)) {
                product.setLiked(true);
            }
        }

        FlashSaleProduct flashSaleProduct = flashSaleProductService.getProductFlashSaleInProgress(product.getId());

        if (flashSaleProduct != null)
            product.setPromotionPercent(flashSaleProduct.getPercentDiscount());

        ProductDetailDTO productDetailDTO = new ProductDetailDTOMapper().mapRow(product, imageDataService);
        productDetailDTO.setFlashSaleProduct(flashSaleProduct);
        return ResponseEntity.ok().body(productDetailDTO);
    }

    @Override
    public ResponseEntity<List<ProductItemDTO>> getProductsByCategory(int idCategory, String filter, int page) {
        Category category = categoryService.findCategoryById(idCategory);

        List<Integer> listId = new ArrayList<>();
//        if (list.size() != 0)
        CategoryUtil.getListIdCategory(category, listId);

//        else listId.add(idCategory);

        Pageable pageable = PageRequest.of(page - 1, 20);

        List<ProductItemDTO> listProduct = new ArrayList<>();

        if (filter.equals("popular")) {
            Page<Product> pageProduct = productService.getProductsByCategoriesOrderByPopular(listId, pageable);
            listProduct = pageProduct.getContent().stream()
                    .map(value -> {
                        ProductItemDTO productItemDTO = new ProductItemDTOMapper().mapRow(value, imageDataService);
                        FlashSaleProduct flashSaleProduct = flashSaleProductService.getProductFlashSaleInProgress(value.getId());

                        if (flashSaleProduct != null)
                            productItemDTO.setPromotionPercent(flashSaleProduct.getPercentDiscount());

                        return productItemDTO;
                    }).collect(Collectors.toList());
        } else {
            if (filter.equals("new")) {
                Page<Product> pageProduct = productService.getProductsByCategoriesOrderByNew(listId, pageable);
                listProduct = pageProduct.getContent().stream()
                        .map(value -> new ProductItemDTOMapper().mapRow(value, imageDataService)).collect(Collectors.toList());
            }
        }

        return ResponseEntity.ok().body(listProduct);
    }

    @Override
    public ResponseEntity<List<ProductItemDTO>> getAllProductFilter(int idCategory, int status, String search, int page, int pageSize) {
        List<Integer> listCategories;
        if (idCategory == 0)
            listCategories = productService.getListIdCategoriesOfProducts();
        else {
            Category category = categoryService.findCategoryById(idCategory);
            listCategories = new ArrayList<>();
            CategoryUtil.getListIdCategory(category, listCategories);
        }

        List<ProductItemDTO> listProducts = productService.getProductFilter(search, status, listCategories, page, pageSize)
                .stream().map(p -> {
                    ProductItemDTO productItemDTO =  new ProductItemDTOMapper().mapRow(p, imageDataService);
                    FlashSaleProduct flashSaleProduct = flashSaleProductService.getProductFlashSaleInProgress(p.getId());

                    if (flashSaleProduct != null)
                        productItemDTO.setPromotionPercent(flashSaleProduct.getPercentDiscount());

                    return productItemDTO;
                }).collect(Collectors.toList());

        return ResponseEntity.ok().body(listProducts);
    }


    @Override
    public int countProductFilter(int idCategory, int status, String search) {
        List<Integer> listCategories;
        if (idCategory == 0)
            listCategories = productService.getListIdCategoriesOfProducts();
        else {
            Category category = categoryService.findCategoryById(idCategory);
            listCategories = new ArrayList<>();
            CategoryUtil.getListIdCategory(category, listCategories);
        }
        return productService.countProductFilter(search, status, listCategories);
    }

    @Override
    public ResponseEntity<String> generateIdRatingStarForProduct() {
        productService.generateIdRatingStar();
        return ResponseEntity.ok().body("done");
    }

    @Override
    public ResponseEntity<List<ProductItemDTO>> getProductTopRating(int userId, int page) {
        long startTime = new Date().getTime();
        List<ProductItemDTO> list;

        if (userId == 0 || !(ratingService.checkUserIsRated(userId) > 0)) {
            list = productService.productTopRating((page - 1) * 10).stream().map(value -> {
                ProductItemDTO productItemDTO = new ProductItemDTOMapper().mapRow(value, imageDataService);
                FlashSaleProduct flashSaleProduct = flashSaleProductService.getProductFlashSaleInProgress(value.getId());

                if (flashSaleProduct != null)
                    productItemDTO.setPromotionPercent(flashSaleProduct.getPercentDiscount());

                return productItemDTO;
            }).collect(Collectors.toList());
        } else {
            if (!recommendRatingService.checkExistUser(userId)) {
                recommend_product_for_user(userId);
            }
            list = productService.productRecommendForUser(userId).stream().map(value -> {
                ProductItemDTO productItemDTO = new ProductItemDTOMapper().mapRow(value, imageDataService);

                FlashSaleProduct flashSaleProduct = flashSaleProductService.getProductFlashSaleInProgress(value.getId());

                if (flashSaleProduct != null)
                    productItemDTO.setPromotionPercent(flashSaleProduct.getPercentDiscount());

                return productItemDTO;
            }).collect(Collectors.toList());
        }

        long endTime = new Date().getTime();
        long difference = endTime - startTime;
        System.out.println("Elapsed time in milliseconds: " + difference);
        return ResponseEntity.ok().body(list);
    }

    @Override
    public ResponseEntity<String> createCosineSimilarity() {
        List<Integer> list_product_rated = ratingService.getProductsRated();

        List<RatingRSDTO> listRatingRS = ratingService.getAll().stream().map(value -> new RatingRSDTOMapper().mapRow(value)).collect(Collectors.toList());

        List<RatingRSDTO> listRatingNormalized = listRatingRS.stream().map(rt -> new RatingRSDTO(rt.getUserId(), rt.getProductId(), rt.getValue())).collect(Collectors.toList());

        List<AVGRatedProductDTO> listAVG = ratingService.calcAVGRatedProduct();

        normalizedRating(list_product_rated, listRatingNormalized, listAVG);

        List<CosineSimilarity> cosSimilarities = new ArrayList<>();

        // calc cosine similarity
        calcCosineSimilarity(list_product_rated, listRatingNormalized, cosSimilarities);

        cosineSimilarityService.removeAll();
        cosineSimilarityService.saveAll(cosSimilarities);
        recommendRatingService.removeAll();
        return ResponseEntity.ok().body("done");
    }

    @Override
    public ResponseEntity<RecommendRating> recommend_product_for_user(int user_id) {
        long startTime = new Date().getTime();

        if (!(ratingService.checkUserIsRated(user_id) > 0)) {
            return ResponseEntity.ok().body(new RecommendRating(user_id, ""));
        }

        List<RatingRSDTO> listRatingRS = ratingService.getAll().stream().map(value -> new RatingRSDTOMapper().mapRow(value)).collect(Collectors.toList());

        List<Integer> list_product = ratingService.getProductsRated();

        List<RatingRSDTO> listRatingNormalized = listRatingRS.stream().map(rt -> new RatingRSDTO(rt.getUserId(), rt.getProductId(), rt.getValue())).collect(Collectors.toList());

        List<AVGRatedProductDTO> listAVG = ratingService.calcAVGRatedProduct();

        // normalized utility matrix
        normalizedRating(list_product, listRatingNormalized, listAVG);

        List<CosineSimilarity> cosSimilarities = cosineSimilarityService.getAll();
        Map<Integer, Double> mapPredictionProduct = new HashMap<>();

        // Rating Prediction
        List<Integer> list_pd_user_rated = new ArrayList<>();
        List<Integer> list_pd_user_unrated = new ArrayList<>();
        divide_rated_unrated(listRatingNormalized, list_product, user_id, list_pd_user_rated, list_pd_user_unrated);
        StringBuilder listProductRS = new StringBuilder();
        StringBuilder listProductRS_Show = new StringBuilder();

        list_pd_user_unrated.parallelStream().forEach(value -> {
            List<CosineSimilarity> list_cos_of_user = new ArrayList<>();
            List<RatingRSDTO> list_normalize_of_user = new ArrayList<>();
            list_cos_of_user = top_k_cosine_similarity_of_user(2, cosSimilarities, value, list_pd_user_rated);

            list_normalize_of_user = top_k_normalized_corresponding_top_k_cosine_similarity(
                    user_id, list_cos_of_user, listRatingNormalized, value);

            double a = list_cos_of_user.get(0).getRow() == list_normalize_of_user.get(0).getProductId() || list_cos_of_user.get(0).getColumn() == list_normalize_of_user.get(0).getProductId() ?
                    list_cos_of_user.get(0).getCosSimilarity() * list_normalize_of_user.get(0).getValue() +
                            list_cos_of_user.get(1).getCosSimilarity() * list_normalize_of_user.get(1).getValue() :
                    list_cos_of_user.get(0).getCosSimilarity() * list_normalize_of_user.get(1).getValue() +
                            list_cos_of_user.get(1).getCosSimilarity() * list_normalize_of_user.get(0).getValue();
            double b = Math.abs(list_cos_of_user.get(0).getCosSimilarity()) + Math.abs(list_cos_of_user.get(1).getCosSimilarity());

            if (a / b > 0) {
                mapPredictionProduct.put(value, a / b);
            }
        });

        mapPredictionProduct.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(10).forEach(value -> {
            listProductRS.append(value.getKey()).append("-");
            listProductRS_Show.append(value.getKey()).append(" - ").append(value.getValue()).append(";");
        });
        if (!recommendRatingService.checkExistUser(user_id))
            recommendRatingService.save(new RecommendRating(user_id, listProductRS.deleteCharAt(listProductRS.length() - 1).toString()));
        else {
            RecommendRating recommendRating = recommendRatingService.getById(user_id);
            recommendRating.setProducts(listProductRS.deleteCharAt(listProductRS.length() - 1).toString());
            recommendRatingService.save(recommendRating);
        }

        long endTime = new Date().getTime();
        long difference = endTime - startTime;
        System.out.println("Elapsed time in milliseconds: " + difference);
        System.out.println("done");

        return ResponseEntity.ok().body(new RecommendRating(user_id, listProductRS_Show.toString()));
    }

    @Override
    public ResponseEntity<AttributeOptionDTO> getAttributeOptionByProduct(int productId) {
        Product product = productService.findProductByIdVisibleTrue(productId);
        AttributeOptionDTO attributeOptionDTO = new AttributeOptionDTOMapper().mapRow(product, imageDataService);
        return ResponseEntity.ok().body(attributeOptionDTO);
    }

    //    @PostMapping("/product/{productId}/{userId}/rating")
//    public ResponseEntity<Rating> ratingProduct(@RequestBody Rating rating, @PathVariable("productId") int product_id, @PathVariable("userId") int user_id) {
//        Random rd = new Random();
//        int idRating;
//        do {
//            idRating = 10000000 + rd.nextInt(6000001);
//        } while (ratingService.checkUserIsRated(idRating) > 0);
//
//        Product pd = productService.findProductById(product_id);
//        Rating new_rating = new Rating();
//        new_rating.setId(idRating);
//        new_rating.setStar(rating.getStar());
//        new_rating.setComment(rating.getComment());
//        new_rating.setUser(userService.getUser(user_id));
//        new_rating.setProduct(pd);
//        new_rating.setTimeCreated(new Timestamp(System.currentTimeMillis()));
//        new_rating.setTimeUpdated(new Timestamp(System.currentTimeMillis()));
//
//        RatingStar ratingStar = ratingStarService.getRatingStarById(pd.getRatingStar().getId());
//        int rt_star = rating.getStar();
//        switch (rt_star) {
//            case 1:
//                ratingStar.setStar1(ratingStar.getStar1() + 1);
//                break;
//            case 2:
//                ratingStar.setStar2(ratingStar.getStar2() + 1);
//                break;
//            case 3:
//                ratingStar.setStar3(ratingStar.getStar3() + 1);
//                break;
//            case 4:
//                ratingStar.setStar4(ratingStar.getStar4() + 1);
//                break;
//            default:
//                ratingStar.setStar5(ratingStar.getStar5() + 1);
//                break;
//
//        }
//
//
//        new_rating = ratingService.save(new_rating);
//        ratingStarService.save(ratingStar);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                createCosineSimilarity();
//            }
//        }).start();
//
//        return ResponseEntity.ok().body(new_rating);
//    }
    private List<RatingRSDTO> calcUserRatedProduct(List<RatingRSDTO> list, int pd) {
        List<RatingRSDTO> a = new ArrayList<>();
        int size_list = list.size();
        for (int i = 0; i < size_list; i++) {
            if (list.get(i).getProductId() == pd) {
                a.add(list.get(i));
            }
        }
//        list.removeAll(a);
        return a;
//        return list.stream().filter(v -> v.getProductId() == pd).collect(Collectors.toList());
    }

    private double p1_dot_p2(List<RatingRSDTO> list1, List<RatingRSDTO> list2) {
        int size_list1 = list1.size();
        int size_list2 = list2.size();
        double p1_dot_p2 = 0.0;
        for (int i = 0; i < size_list1; i++) {
            for (int j = 0; j < size_list2; j++) {
                RatingRSDTO rt1 = list1.get(i);
                RatingRSDTO rt2 = list2.get(j);
                if (rt1.getUserId() == rt2.getUserId()) {
                    p1_dot_p2 += (rt1.getValue() * rt2.getValue());
                    break;
                }
            }
        }
        return p1_dot_p2;
    }

    private double calc_norm(List<RatingRSDTO> list) {
        int size_list = list.size();
        double norm = 0.0;
        for (int i = 0; i < size_list; i++) {
            RatingRSDTO rt = list.get(i);
            if (rt.getValue() != 0.0) {
                norm += Math.pow(rt.getValue(), 2);
            }
        }
        return Math.sqrt(norm);
    }

    private void divide_rated_unrated(List<RatingRSDTO> list_normalized, List<Integer> list_product, int user_id, List<Integer> list_rated, List<Integer> list_unrated) {
        int size_list_product = list_product.size();
        list_rated.clear();
        list_unrated.clear();
        for (int i = 0; i < size_list_product; i++) {
            int pd = list_product.get(i);
//            if (list_normalized.contains(new RatingRSDTO(user_id, pd)))
            if (list_normalized.stream().filter(value -> value.getUserId() == user_id && value.getProductId() == pd).findAny().orElse(null) != null)
                list_rated.add(pd);
            else list_unrated.add(pd);
        }
    }

    private List<CosineSimilarity> top_k_cosine_similarity_of_user(int k, List<CosineSimilarity> list, int pd_unrated_id, List<Integer> list_pd_user_rated) {
//        List<CosineSimilarity> cos_list = new ArrayList<>();
//        int size_list = list.size();
        return list.stream().filter(cos -> (cos.getRow() == pd_unrated_id && list_pd_user_rated.contains(cos.getColumn())) ||
                (list_pd_user_rated.contains(cos.getRow()) && cos.getColumn() == pd_unrated_id)).limit(k).collect(Collectors.toList());
//        for (int i = 0; i < size_list; i++) {
//            CosineSimilarity cos = list.get(i);
//            if ((cos.getRow() == pd_unrated_id && list_pd_user_rated.contains(cos.getColumn())) ||
//                    (list_pd_user_rated.contains(cos.getRow()) && cos.getColumn() == pd_unrated_id)) {
//                cos_list.add(cos);
//            }
//        }
//
////        cosSimilarities.parallelStream().
////                filter(value -> (value.getRow() == pd_unrated_id && list_pd_user_rated.contains(value.getColumn())) || (list_pd_user_rated.contains(value.getRow()) && value.getColumn() == pd_unrated_id)).
////                sorted(Comparator.comparingDouble(CosineSimilarity::getSimilarity).reversed()).
////                limit(2).collect(Collectors.toList());
//
//        return cos_list.stream().sorted(Comparator.comparing(CosineSimilarity::getSimilarity)).limit(k).collect(Collectors.toList());
    }

    private List<RatingRSDTO> top_k_normalized_corresponding_top_k_cosine_similarity(int user_id, List<CosineSimilarity> list_cos_of_user, List<RatingRSDTO> listRatingNormalized, int pd_unrated_id) {
//        List<RatingRSDTO> list = new ArrayList<>();
//        int size_normalized = listRatingNormalized.size();
//        for (int i = 0; i < size_normalized; i++) {
//            RatingRSDTO nm = listRatingNormalized.get(i);
//            if (nm.getUserId() == user_id && (list_cos_of_user.contains(new CosineSimilarity(pd_unrated_id, nm.getProductId())) ||
//                    list_cos_of_user.contains(new CosineSimilarity(nm.getProductId(), pd_unrated_id)))) {
//                list.add(nm);
//                if (list.size() == 2) return list;
//            }
//        }
//        return list;
        return listRatingNormalized.stream().filter(nm -> nm.getUserId() == user_id && ((list_cos_of_user.stream().filter(value -> value.getRow() == pd_unrated_id && value.getColumn() == nm.getProductId()).findAny().orElse(null) != null) ||
                list_cos_of_user.stream().filter(value -> value.getRow() == nm.getProductId() && value.getColumn() == pd_unrated_id).findAny().orElse(null) != null)).limit(2).collect(Collectors.toList());
//        listRatingNormalized.parallelStream().
//                filter(value -> value.getUserId() == user_id && (list_cos_of_user.contains(new CosineSimilarity(pd_unrated_id, value.getProductId())) ||
//                        list_cos_of_user.contains(new CosineSimilarity(value.getProductId(), pd_unrated_id)))).
//                limit(2).collect(Collectors.toList());
    }

//    private void getListIdCategory(Set<Category> list, List<Integer> listId) {
//        if (list.size() == 0)
//            return;
//        list.forEach(c -> {
//            Set<Category> listCAT = c.getCategories();
//            if (listCAT.size() > 0) {
//                getListIdCategory(listCAT, listId);
//            } else {
//                listId.add(c.getId());
//            }
//        });
//    }

    private void calcCosineSimilarity(List<Integer> list_product_rated, List<RatingRSDTO> listRatingNormalized, List<CosineSimilarity> cosSimilarities) {
        int size_list = list_product_rated.size();
        for (int i = size_list - 1; i > 0; i--) {
            int product_id1 = list_product_rated.get(i);
            List<RatingRSDTO> user_rated_product1 = calcUserRatedProduct(listRatingNormalized, product_id1);
            for (int j = i - 1; j >= 0; j--) {
                int product_id2 = list_product_rated.get(j);
                List<RatingRSDTO> user_rated_product2 = calcUserRatedProduct(listRatingNormalized, product_id2);

                double pd1_dot_pd2 = p1_dot_p2(user_rated_product1, user_rated_product2);
                double norm_pd1 = calc_norm(user_rated_product1);
                double norm_pd2 = calc_norm(user_rated_product2);
                double norm_pd1_mul_norm_pd2 = norm_pd1 * norm_pd2;
                double cosineSimilarity = norm_pd1_mul_norm_pd2 != 0.0 ? pd1_dot_pd2 / norm_pd1_mul_norm_pd2 : -1;

                CosineSimilarity a = new CosineSimilarity(product_id1, product_id2, cosineSimilarity);
                cosSimilarities.add(a);
                System.out.println("i: " + i + " - j: " + j);
            }
        }
    }

    private void normalizedRating(List<Integer> list_product, List<RatingRSDTO> listRatingNormalized, List<AVGRatedProductDTO> listAVG) {
        int size_list_avg = listAVG.size();
        for (int i = 0; i < size_list_avg; i++) {
            int pd_id = list_product.get(i);
            double avg_pd = listAVG.get(i).getAvgRated();
            listRatingNormalized.parallelStream().
                    filter(value -> value.getProductId() == pd_id).
                    forEach(value -> {
                        value.setValue(value.getValue() - avg_pd);
                    });
        }
    }


    @Override
    public ResponseEntity<List<RecommendForUser>> recommend_movie_test() {
        long startTime = new Date().getTime();

        List<Integer> list_users = new ArrayList<>();
        list_users.add(0);
        list_users.add(1);
        list_users.add(2);
        list_users.add(3);
        list_users.add(4);
        list_users.add(5);
        list_users.add(6);
        List<Integer> list_product = new ArrayList<>();
        list_product.add(0);
        list_product.add(1);
        list_product.add(2);
        list_product.add(3);
        list_product.add(4);

        List<RatingRSDTO> listRatingRS = new ArrayList<>();
        listRatingRS.add(new RatingRSDTO(0, 0, 5.));
        listRatingRS.add(new RatingRSDTO(0, 1, 4.));
        listRatingRS.add(new RatingRSDTO(0, 3, 2.));
        listRatingRS.add(new RatingRSDTO(0, 4, 2.));
        listRatingRS.add(new RatingRSDTO(1, 0, 5.));
        listRatingRS.add(new RatingRSDTO(1, 2, 4.));
        listRatingRS.add(new RatingRSDTO(1, 3, 2.));
        listRatingRS.add(new RatingRSDTO(1, 4, 1.));
        listRatingRS.add(new RatingRSDTO(2, 0, 2.));
        listRatingRS.add(new RatingRSDTO(2, 2, 1.));
        listRatingRS.add(new RatingRSDTO(2, 3, 3.));
        listRatingRS.add(new RatingRSDTO(2, 4, 4.));
        listRatingRS.add(new RatingRSDTO(3, 0, 1.));
        listRatingRS.add(new RatingRSDTO(3, 1, 1.));
        listRatingRS.add(new RatingRSDTO(3, 3, 4.));
        listRatingRS.add(new RatingRSDTO(4, 0, 1.));
        listRatingRS.add(new RatingRSDTO(4, 3, 4.));
        listRatingRS.add(new RatingRSDTO(5, 1, 2.));
        listRatingRS.add(new RatingRSDTO(5, 2, 1.));
        listRatingRS.add(new RatingRSDTO(6, 2, 1.));
        listRatingRS.add(new RatingRSDTO(6, 3, 4.));
        listRatingRS.add(new RatingRSDTO(6, 4, 5.));


        List<RatingRSDTO> listRatingNormalized = listRatingRS.stream().map(rt -> new RatingRSDTO(rt.getUserId(), rt.getProductId(), rt.getValue())).collect(Collectors.toList());

//        List<AVGRatedProductDTO> listAVG = ratingService.calcAVGRatedProduct();
        List<AVGRatedProductDTO> listAVG = new ArrayList<>();
        listAVG.add(new AVGRatedProductDTO(0, 2.8));
        listAVG.add(new AVGRatedProductDTO(1, 7.0 / 3.0));
        listAVG.add(new AVGRatedProductDTO(2, 1.75));
        listAVG.add(new AVGRatedProductDTO(3, 19.0 / 6.0));
        listAVG.add(new AVGRatedProductDTO(4, 3.0));

        // normalized utility matrix
        int size_list_avg = listAVG.size();
        int size_list_user = list_users.size();
        for (int i = 0; i < size_list_avg; i++) {
            int pd_id = list_product.get(i);
            double avg_pd = listAVG.get(i).getAvgRated();
            listRatingNormalized.parallelStream().filter(value -> value.getProductId() == pd_id).forEach(value -> {
                value.setValue(value.getValue() - avg_pd);
            });
        }

        List<CosineSimilarity> cosSimilarities = new ArrayList<>();

        // calc cosine similarity
        for (int i = size_list_avg - 1; i > 0; i--) {
            int product_id1 = list_product.get(i);
            List<RatingRSDTO> user_rated_product1 = calcUserRatedProduct(listRatingNormalized, product_id1);
            for (int j = i - 1; j >= 0; j--) {

                for (int k = 0; k < list_users.size(); k++) {

                }
                int product_id2 = list_product.get(j);
                List<RatingRSDTO> user_rated_product2 = calcUserRatedProduct(listRatingNormalized, product_id2);

                double pd1_dot_pd2 = p1_dot_p2(user_rated_product1, user_rated_product2);
                double norm_pd1 = calc_norm(user_rated_product1);
                double norm_pd2 = calc_norm(user_rated_product2);
                double norm_pd1_mul_norm_pd2 = norm_pd1 * norm_pd2;
//
                double cosineSimilarity = norm_pd1_mul_norm_pd2 != 0.0 ? pd1_dot_pd2 / norm_pd1_mul_norm_pd2 : -1;
//
                CosineSimilarity a = new CosineSimilarity(product_id1, product_id2, cosineSimilarity);
                cosSimilarities.add(a);
                System.out.println("i: " + i + " - j: " + j);
            }
        }

        for (CosineSimilarity cs : cosSimilarities) {
            System.out.println(cs.toString());
        }

        cosSimilarities.forEach(System.out::println);

        List<RecommendForUser> recommendForUserList = new ArrayList<>();
//
//        // Rating Prediction
        List<Integer> list_pd_user_rated = new ArrayList<>();
        List<Integer> list_pd_user_unrated = new ArrayList<>();
//        List<CosineSimilarity> list_cos_of_user = new ArrayList<>();
//        List<RatingRSDTO> list_normalize_of_user = new ArrayList<>();
        for (int i = 0; i < size_list_user; i++) {
            int user_id = list_users.get(i);
            divide_rated_unrated(listRatingNormalized, list_product, user_id, list_pd_user_rated, list_pd_user_unrated);
            System.out.println("------------------------giai doan 1---------------------------");
            int size_unrated = list_pd_user_unrated.size();
//            int size_rated = list_pd_user_rated.size();
            StringBuilder listProductRS = new StringBuilder();
            list_pd_user_unrated.parallelStream().forEach(value -> {
                List<CosineSimilarity> list_cos_of_user = new ArrayList<>();
                List<RatingRSDTO> list_normalize_of_user = new ArrayList<>();
                list_cos_of_user = top_k_cosine_similarity_of_user(2, cosSimilarities, value, list_pd_user_rated);

                list_normalize_of_user = top_k_normalized_corresponding_top_k_cosine_similarity(
                        user_id, list_cos_of_user, listRatingNormalized, value);

                double a = list_cos_of_user.get(0).getRow() == list_normalize_of_user.get(0).getProductId() || list_cos_of_user.get(0).getColumn() == list_normalize_of_user.get(0).getProductId() ?
                        list_cos_of_user.get(0).getCosSimilarity() * list_normalize_of_user.get(0).getValue() +
                                list_cos_of_user.get(1).getCosSimilarity() * list_normalize_of_user.get(1).getValue() :
                        list_cos_of_user.get(0).getCosSimilarity() * list_normalize_of_user.get(1).getValue() +
                                list_cos_of_user.get(1).getCosSimilarity() * list_normalize_of_user.get(0).getValue();
                double b = Math.abs(list_cos_of_user.get(0).getCosSimilarity()) + Math.abs(list_cos_of_user.get(1).getCosSimilarity());

//                System.out.println(a / b);
                if (a / b > 0) {
                    listProductRS.append(value).append(" (").append((double) Math.round((a / b) * 100) / 100).append("), ");
                }
            });
            recommendForUserList.add(new RecommendForUser(user_id, listProductRS.toString()));

//        System.out.println("i: " + i + "/" + size_list_user);
        }
//
        recommendForUserList.forEach(System.out::println);


        long endTime = new Date().getTime();
        long difference = endTime - startTime;
        System.out.println("Elapsed time in milliseconds: " + difference);
        System.out.println("done");

        return ResponseEntity.ok().body(recommendForUserList);
    }

    @Override
    public List<HashMap<String, Double>> calculationContentBasedTest(String search) {
        return productService.calcContentBasedTest(search);
    }

    @Override
    public List<SearchProductDTO> searchProduct(String search, int page) {
        return productService.getProductSearch(search, imageDataService, page);
    }

    @Override
    public List<SearchProductDTO> searchProduct(int userId, String search, int page) {
        historySearchService.createOrUpdateHistorySearch(userId, search);
        return productService.getProductSearch(search, imageDataService, page);
    }

    @Override
    public List<ProductItemDTO> productsSimilarity(int productId, int page) {
        return productService.getProductsSimilarity(productId, imageDataService, page);
    }

    @Override
    public List<ProductItemDTO> productsMightAlsoLike(int userId, int page) {
        return productService.getProductsAlsoLike(userId, imageDataService, page);
    }

    @Override
    public ResponseEntity<String> reviewProduct(int user_id, InputReviewProductDTO inputReview) {
        productService.reviewProduct(user_id, inputReview);
        return ResponseEntity.ok().body("đánh giá thành công");
    }
}