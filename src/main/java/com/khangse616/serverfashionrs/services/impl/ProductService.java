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

//        list.add("bây giờ mận mới hỏi đào");
//        list.add("vườn hồng có lối ai vào hay chưa");
        TfidfCalculation TfidfObj = new TfidfCalculation();
        int noOfDocs = list.size();

        //containers for documents and their properties required to calculate final score
        DocumentProperties[] docProperties = new DocumentProperties[noOfDocs];
        int count = 0;
        for (String des : list) {
            docProperties[count] = new DocumentProperties();
            HashMap<String, Integer> wordCount = TfidfObj.getTerms(des);
            docProperties[count].setWordCountMap(wordCount);
            HashMap<String, Double> termFrequency = TfidfObj.calculateTermFrequency(docProperties[count].getWordCountMap());
            docProperties[count].setTermFreqMap(termFrequency);
            count++;
        }

        //calculating InverseDocument frequency
        HashMap<String, Double> inverseDocFreqMap = TfidfObj.calculateInverseDocFrequency(docProperties);

        //Calculating tf-idf
        List<HashMap<String, Double>> listTFIDF = new ArrayList<>();
        count = 0;
        for (int i = 0; i < list.size(); i++) {
            HashMap<String, Double> tfIDF = new HashMap<>();
            double tfIdfValue = 0.0;
            double idfVal = 0.0;

            HashMap<String, Double> tf = docProperties[count].getTermFreqMap();
            Iterator itTF = tf.entrySet().iterator();
            while (itTF.hasNext()) {
                Map.Entry pair = (Map.Entry) itTF.next();
                double tfVal = (Double) pair.getValue();
                if (inverseDocFreqMap.containsKey((String) pair.getKey())) {
                    idfVal = inverseDocFreqMap.get((String) pair.getKey());
                }
                tfIdfValue = tfVal * idfVal;
                tfIDF.put((pair.getKey().toString()), tfIdfValue);
            }

            listTFIDF.add(tfIDF);
            count++;
        }

        return listTFIDF;
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findById(id);
    }
}
