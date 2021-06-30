package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.Utils.ImageUtil;
import com.khangse616.serverfashionrs.Utils.RecommendSystemUtil;
import com.khangse616.serverfashionrs.mappers.impl.ProductItemDTOMapper;
import com.khangse616.serverfashionrs.mappers.impl.SearchProductDTOMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.InputReviewProductDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.DescriptionCountDTO;
import com.khangse616.serverfashionrs.models.dto.SearchProductDTO;
import com.khangse616.serverfashionrs.repositories.ProductRepository;
import com.khangse616.serverfashionrs.services.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IRatingStarService ratingStarService;

    @Autowired
    private IRecommendRatingService recommendRatingService;

    @Autowired
    private IRatingService ratingService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderItemService orderItemService;

    @Autowired
    private IImageDataService imageDataService;

    @Override
    public Product findProductByIdVisibleTrue(int id) {
        return productRepository.findByIdAndVisibilityTrue(id);
    }

    @Override
    public List<Product> getAllProductVisibility() {
        return productRepository.getProductsVisibilityTrue();
    }

    @Override
    public List<Product> getAllProductVisibilityOrderByPromotionPercent() {
        return productRepository.findAllByVisibilityTrueOrderByPromotionPercentDesc();
    }

    @Override
    public Page<Product> getProductsByCategoriesOrderByNew(List<Integer> idCategories, Pageable pageable) {
        return productRepository.findProductByCategoriesOrderByNew(idCategories, pageable);
    }

    @Override
    public Page<Product> getProductsByCategoriesOrderByPopular(List<Integer> idCategories, Pageable pageable) {
        return productRepository.findProductByCategoriesOrderByPopular(idCategories, pageable);
    }

    @Override
    public void generateIdRatingStar() {
        List<Product> list = productRepository.findAllByVisibilityTrueOrderByPromotionPercentDesc();

        Random rd = new Random();

        list.forEach(l -> {
            int idRatingStar;
            do {
                idRatingStar = 100 + rd.nextInt(6000001);
            } while (ratingStarService.existRatingStarId(idRatingStar));

            RatingStar ratingStar = ratingStarService.save(new RatingStar(idRatingStar));

            l.setRatingStar(ratingStar);
        });

        productRepository.saveAll(list);
    }

    @Override
    public List<Product> productTopRating(int page) {
        float C = productRepository.meanOfVoteAverage();
        float m = productRepository.calculateQuantile();

        return productRepository.topRatingProducts(m, C, page);
    }

    @Override
    public List<Product> productRecommendForUser(int userId) {
        RecommendRating recommendRating = recommendRatingService.findRecommendRatingByUserId(userId);
        List<Integer> listProducts = Arrays.stream(recommendRating.getProducts().split("-")).map(Integer::parseInt).collect(Collectors.toList());
        return productRepository.findProductByListIdProduct(listProducts);
    }

    @Override
    public List<HashMap<String, Double>> calcContentBasedTest(String textTest) {

        List<Product> list = productRepository.getProductsVisibilityTrue();

        HashMap<Product, Double> listProductSearch = RecommendSystemUtil.calcCosineSimilarityText(textTest, list);

        System.out.println(listProductSearch.size());

        HashMap<Product, Double> testSearch = listProductSearch.entrySet().stream().sorted(Map.Entry.<Product, Double>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        testSearch.forEach((k, v) -> {
            System.out.println(k.getName());
            System.out.println(v);
            System.out.println("____---------------------------_____");
        });

        return null;
    }

    @Override
    public List<SearchProductDTO> getProductSearch(String search, IImageDataService imageDataService, int page) {
        List<Product> list = productRepository.getProductsVisibilityTrue();

        System.out.println(page);

        return RecommendSystemUtil.calcCosineSimilarityText(search, list).entrySet().stream().sorted(Map.Entry.<Product, Double>comparingByValue().reversed())
                .skip(page* 15L)
                .limit(15)
                .map(entry -> new SearchProductDTOMapper().mapRow(entry, imageDataService))
                .collect(Collectors.toList());
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductItemDTO> getProductsSimilarity(int id, IImageDataService imageDataService, int page) {
        String shortDescOrName = productRepository.getShortDescriptionOrName(id);
        List<Product> list = productRepository.getProductAndShortDescriptionExceptProduct(id);

        return RecommendSystemUtil.calcCosineSimilarityText(shortDescOrName, list).entrySet().stream()
                .sorted(Map.Entry.<Product, Double>comparingByValue().reversed())
                .skip(page* 15L)
                .limit(15)
                .map(v -> new ProductItemDTOMapper().mapRow(v.getKey(), imageDataService))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductItemDTO> getProductsAlsoLike(int userId, IImageDataService imageDataService, int page) {
        List<Integer> listIdProduct = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Object[]> pageSeen = productRepository.getShortDescriptionOrNameByUser(userId, pageable);
        List<DescriptionCountDTO> listSeen = pageSeen.getContent().stream().map(v -> {
            listIdProduct.add((Integer) v[2]);
            return new DescriptionCountDTO((String) v[0], (int)v[1]);
        }).collect(Collectors.toList());

        List<Product> list = productRepository.getProductAndShortDescriptionExceptListProduct(listIdProduct);
        int noOfDocs = list.size();

        TfidfCalculation TfidfObj = new TfidfCalculation();

        //containers for documents and their properties required to calculate final score
        DocumentProperties[] docProperties = new DocumentProperties[noOfDocs];
        SortedSet<String> wordList = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < noOfDocs; i++) {
            docProperties[i] = TfidfObj.calculateTF(list.get(i), wordList);
        }

        //calculating InverseDocument frequency
        HashMap<String, Double> inverseDocFreqMap = TfidfObj.calculateInverseDocFrequency(docProperties, wordList);

        //Calculating tf-idf
        HashMap<Product, HashMap<String, Double>> listTFIDF = new HashMap<>();
        for (int i = 0; i < noOfDocs; i++) {
            listTFIDF.put(list.get(i), TfidfObj.calculateTFIDF(docProperties[i], inverseDocFreqMap));
        }

        SortedSet<String> wordListSearch = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        DocumentProperties documentProperty = TfidfObj.calculateTF(listSeen, wordListSearch);

        HashMap<String, Double> tfidfSearch = TfidfObj.calculateTFIDF(documentProperty, inverseDocFreqMap);

        HashMap<Product, Double> listProductAlsoLike = new HashMap<>();

        for (Map.Entry<Product, HashMap<String, Double>> pd : listTFIDF.entrySet()) {
            Iterator<Map.Entry<String, Double>> it = tfidfSearch.entrySet().iterator();
            double dot_pd = 0.0;
            double norm_search = 0.0;
            while (it.hasNext()) {
                Map.Entry<String, Double> pair = it.next();
                if (pd.getValue().containsKey((String) pair.getKey())) {
                    dot_pd += pd.getValue().get(pair.getKey()) * (double) pair.getValue();
                }
                norm_search += (double) pair.getValue() * (double) pair.getValue();
            }
            double norm_pd = 0.0;
            for (double v : pd.getValue().values()) {
                norm_pd += v * v;
            }

            double cosine = dot_pd / (Math.sqrt(norm_pd) * Math.sqrt(norm_search));
            if (cosine > 0.0)
                listProductAlsoLike.put(pd.getKey(), cosine);
        }

        System.out.println(listProductAlsoLike.size());

        return listProductAlsoLike.entrySet().stream()
                .sorted(Map.Entry.<Product, Double>comparingByValue().reversed())
                .skip(page* 15L)
                .limit(15)
                .map(v -> new ProductItemDTOMapper().mapRow(v.getKey(), imageDataService))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void reviewProduct(int userId, InputReviewProductDTO inputReview) {
        Rating rating = new Rating();
        Random rd = new Random();
        int idRating;
        do {
            idRating = 100 + rd.nextInt(6000001);
        } while (ratingService.checkExistId(idRating));

        rating.setId(idRating);
        rating.setStar(inputReview.getStar());
        rating.setComment(inputReview.getComment());
        rating.setUser(userService.getUserById(userId));
        OrderItem orderItem = orderItemService.getOrderItem(inputReview.getOrderItem());
        orderItem.setReviewStatus(true);
        orderItemService.save(orderItem);
        rating.setProduct(orderItem.getProduct());
        rating.setProductAttribute(orderItem.getProductOption());
        rating.setTimeCreated(new Timestamp(System.currentTimeMillis()));
        rating.setTimeUpdated(new Timestamp(System.currentTimeMillis()));
        rating.setIncognito(inputReview.getIncognito());

        Rating ratingSave = ratingService.save(rating);

        new Thread(new Runnable() {
            @Override
            public void run() {
                saveImage(ratingSave, imageDataService, inputReview.getListImage(), ratingService);
            }
        }).start();

//        saveImage(rating, imageDataService, inputReview.getListImage(), ratingService);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Page<Product> getProductByCategoryOrderPopular(int id, Pageable pageable) {
        return productRepository.findProductByCategoryIdAndVisibilityTrueOrderByPurpose(id, pageable);
    }

    @SneakyThrows
    private void saveImage(Rating rating, IImageDataService imageDataService, List<MultipartFile> files, IRatingService ratingService){
        List<MultipartFile> multipartFiles = new ArrayList<>();
        files.forEach(file -> {
            String fileName = ImageUtil.fileName(imageDataService, file);
            try {
                MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, file.getContentType(), file.getInputStream());
                multipartFiles.add(multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        List<ImageData> imageDataList = imageDataService.storesImageData(multipartFiles);

        rating.setDataImages(new HashSet<>(imageDataList));
        ratingService.save(rating);
    }
}
