package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.AttributeOptionDTO;
import com.khangse616.serverfashionrs.repositories.ProductRepository;
import com.khangse616.serverfashionrs.services.IProductService;
import com.khangse616.serverfashionrs.services.IRatingService;
import com.khangse616.serverfashionrs.services.IRatingStarService;
import com.khangse616.serverfashionrs.services.IRecommendRatingService;
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
//        List<String> list = productRepository.getShortDescriptionByVisibilityTrue();
        List<String> list = new ArrayList<>();
        list.add("Người lên ngựa kẻ chia bào. Rừng phong thu đã nhốm màu quan san");
        list.add("Ô hay buồn vương cây ngô đồng. Vàng rơi vàng rơi thu mênh mông");
        list.add("Một chiều về bên bến sông thu. Nghe tin em cưới á cái đù.");

//        list.add("sông thu");

//        list.add("bây giờ mận mới hỏi đào");
//        list.add("vườn hồng có lối ai vào hay chưa");
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
        List<HashMap<String, Double>> listTFIDF = new ArrayList<>();
        for (int i = 0; i < noOfDocs; i++) {
            listTFIDF.add(TfidfObj.calculateTFIDF(docProperties[i], inverseDocFreqMap));
        }

        String search = "sông thu";
        SortedSet<String> wordListSearch = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        DocumentProperties documentProperty = TfidfObj.calculateTF(search, wordListSearch);

//        List<HashMap<String, Double>> listTFIDFSearch = new ArrayList<>();

        HashMap<String, Double> tfidfSearch = TfidfObj.calculateTFIDF(documentProperty, inverseDocFreqMap);

        for (HashMap<String, Double> tfidfProduct : listTFIDF) {
            Iterator it = tfidfSearch.entrySet().iterator();
            double dot_pd = 0.0;
            double norm_search = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (tfidfProduct.containsKey((String) pair.getKey())) {
                    dot_pd += tfidfProduct.get(pair.getKey()) * (double) pair.getValue();
                }
                norm_search += (double) pair.getValue() * (double) pair.getValue();
            }
            double norm_pd = 0.0;
            for(double v:tfidfProduct.values()){
                norm_pd += v;
            }

            double cosine = dot_pd/(Math.sqrt(norm_pd)*Math.sqrt(norm_search));
            System.out.println(cosine);
        }


        return listTFIDF;
    }


    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id);
    }
}
