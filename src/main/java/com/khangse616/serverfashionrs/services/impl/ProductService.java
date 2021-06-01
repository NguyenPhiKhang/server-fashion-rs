package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.Utils.MapUtil;
import com.khangse616.serverfashionrs.mappers.impl.ProductItemDTOMapper;
import com.khangse616.serverfashionrs.mappers.impl.SearchProductDTOMapper;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.AttributeOptionDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import com.khangse616.serverfashionrs.models.dto.SearchProductDTO;
import com.khangse616.serverfashionrs.repositories.ProductRepository;
import com.khangse616.serverfashionrs.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Override
    public Product findProductByIdVisibleTrue(int id) {
        return productRepository.findByIdAndVisibilityTrue(id);
    }

    @Override
    public List<Product> getAllProductVisibility() {
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
    public List<HashMap<String, Double>> getListShortDescription() {
        List<Product> list = productRepository.getProductAndShortDescription();
        TfidfCalculation TfidfObj = new TfidfCalculation();
        int noOfDocs = list.size();

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

        String search = "Đồng hồ";
        SortedSet<String> wordListSearch = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        DocumentProperties documentProperty = TfidfObj.calculateTF(search, wordListSearch);

        HashMap<String, Double> tfidfSearch = TfidfObj.calculateTFIDF(documentProperty, inverseDocFreqMap);

        HashMap<Product, Double> listProductSearch = new HashMap<>();

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
                listProductSearch.put(pd.getKey(), cosine);
        }

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
    public List<SearchProductDTO> getProductSearch(String search, IImageDataService imageDataService) {
        List<Product> list = productRepository.getProductAndShortDescription();
        TfidfCalculation TfidfObj = new TfidfCalculation();
        int noOfDocs = list.size();

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
        DocumentProperties documentProperty = TfidfObj.calculateTF(search, wordListSearch);

        HashMap<String, Double> tfidfSearch = TfidfObj.calculateTFIDF(documentProperty, inverseDocFreqMap);

        HashMap<Product, Double> listProductSearch = new HashMap<>();

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
                listProductSearch.put(pd.getKey(), cosine);
        }

        System.out.println(listProductSearch.size());

        return listProductSearch.entrySet().stream().sorted(Map.Entry.<Product, Double>comparingByValue().reversed())
                .limit(10)
                .map(entry -> new SearchProductDTOMapper().mapRow(entry, imageDataService))
                .collect(Collectors.toList());
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id);
    }
}
