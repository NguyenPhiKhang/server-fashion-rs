package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.Utils.ImageUtil;
import com.khangse616.serverfashionrs.models.*;
import com.khangse616.serverfashionrs.models.dto.CountRatingProductDTO;
import com.khangse616.serverfashionrs.models.dto.InputRatingUpdateDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.AVGRatedProductDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.RatingRSDTO;
import com.khangse616.serverfashionrs.repositories.ProductRepository;
import com.khangse616.serverfashionrs.repositories.RatingRepository;
import com.khangse616.serverfashionrs.repositories.RatingStarRepository;
import com.khangse616.serverfashionrs.repositories.UserRepository;
import com.khangse616.serverfashionrs.services.IImageDataService;
import com.khangse616.serverfashionrs.services.IRatingService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RatingService implements IRatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RatingStarRepository ratingStarRepository;
    @Autowired
    private IImageDataService imageDataService;

    @Override
    public int checkUserIsRated(int uId) {
        return ratingRepository.existsByUserId(uId);
    }

    @Override
    public boolean checkExistId(int id) {
        return ratingRepository.existsById(id);
    }

    @Override
    public int numberUserInRatings() {
        return ratingRepository.countDistinctAllUser();
    }

    @Override
    public int numberProductInRatings() {
        return ratingRepository.countDistinctAllProduct();
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAllByOrderByProductAsc();
    }

    @Override
    public List<Integer> getUsersRated() {
        return ratingRepository.findUsersRated();
    }

    @Override
    public List<Integer> getProductsRated() {
        return ratingRepository.findProductsRated();
    }

    public List<AVGRatedProductDTO> calcAVGRatedProduct() {
        return ratingRepository.avgRatedProduct();
    }

    @Override
    public void autoRating() {
        Random rd = new Random();
        List<User> users = userRepository.findAll();
        List<Product> products = productRepository.findAllByVisibilityTrueOrderByPromotionPercentDesc();

        int size_product = products.size() - 100;
        List<Rating> list_ratings = new ArrayList<>();
        List<RatingStar> ratingStarList = new ArrayList<>();
        int q = 100;
        for (int j = 0; j < size_product; j++) {
            System.out.println("-----------------product thu: " + j + " ------------------------");
            Product pd = products.get(j);
            int rating_rd = rd.nextInt(501);
            rating_rd = q > 0 ? rating_rd : rating_rd + q;
            q *= -1;
            int total_rating = rating_rd < 0 ? 0 : Math.min(rating_rd, 500);
            System.out.println("tong rating: " + total_rating);
            int star1 = 0;
            int star2 = 0;
            int star3 = 0;
            int star4 = 0;
            int star5 = 0;
            for (int i = 0; i < total_rating; i++) {
                int idRating;
                do {
                    idRating = 10000000 + rd.nextInt(6000001);
                } while (ratingRepository.existsById(idRating));

                int star = 1 + rd.nextInt(13);
                switch (star) {
                    case 1:
                        star1++;
                        break;
                    case 2:
                        star2++;
                        break;
                    case 3:
                        star3++;
                        break;
                    case 4:
                        star4++;
                        break;
                    default:
                        star5++;
                        break;

                }
                int userIndex;
                User user;
                do {
                    userIndex = rd.nextInt(users.size());
                    user = users.get(userIndex);
                } while (ratingRepository.existsByUserAndProduct(user, pd));


                Rating rating = new Rating();
                rating.setId(idRating);
                rating.setStar(Math.min(star, 5));
                rating.setUser(user);
                rating.setProduct(pd);
                rating.setTimeCreated(new Timestamp(System.currentTimeMillis()));
                rating.setTimeUpdated(new Timestamp(System.currentTimeMillis()));

                list_ratings.add(rating);

//                ratingRepository.save(rating);
            }

            RatingStar ratingStar = pd.getRatingStar();
            ratingStar.setStar1(star1);
            ratingStar.setStar2(star2);
            ratingStar.setStar3(star3);
            ratingStar.setStar4(star4);
            ratingStar.setStar5(star5);

            ratingStarList.add(ratingStar);
        }

        ratingRepository.saveAll(list_ratings);
        ratingStarRepository.saveAll(ratingStarList);
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatingByProductId(int productId, int page) {
        return ratingRepository.findAllRatingsByProductId(productId, page);
    }

    @Override
    public List<Rating> getRatingByProductIdAndStar(int productId, int star, int page) {
        return ratingRepository.findRatingsByProductIdAndStar(productId, star, page);
    }

    @Override
    public CountRatingProductDTO countRatingByStarOfProduct(int productId) {
        return ratingRepository.countRatingByStar(productId);
    }

    @Override
    public int countRatingByImageOfProduct(int productId) {
        return ratingRepository.countRatingByImage(productId);
    }

    @Override
    public List<Rating> getRatingByProductIdHasImage(int productId, int page) {
        return ratingRepository.findRatingsByProductIdAndHasImage(productId, page);
    }

    @Override
    public void autoInsertRating() {
        List<Product> products = productRepository.findAllByVisibilityTrueOrderByPromotionPercentDesc();

        Random rd = new Random();

        products.forEach(v -> {
            List<Product> productAttribute = new ArrayList<>(v.getProductLinks());

            v.getRatings().forEach(r -> {
                if (productAttribute.size() != 0) {
                    int pd_rd = rd.nextInt(productAttribute.size());
                    r.setProductAttribute(productAttribute.get(pd_rd));
                } else r.setProductAttribute(v);
            });

            ratingRepository.saveAll(v.getRatings());
        });

//        Product product = productRepository.findByIdAndVisibilityTrue(48119);
//        Random rd = new Random();
//
//        List<Product> productAttribute = new ArrayList<>(product.getProductLinks());
//
//        product.getRatings().forEach(r->{
//            int pd_rd = rd.nextInt(productAttribute.size());
//            r.setProductAttribute(productAttribute.get(pd_rd));
//        });
//
//        ratingRepository.saveAll(product.getRatings());
    }

    @Override
    public List<RatingRSDTO> getUserLeftJoinRating() {
        List<RatingRSDTO> ratingRSDTOList = new ArrayList<>();

        List<Object[]> objects = ratingRepository.getUserLeftJoinRating();

        for (Object[] o : objects) {
            RatingRSDTO dto = new RatingRSDTO();
            dto.setUserId((int) o[0]);
            if (o[1] == null && o[2] == null) {
                dto.setProductId(0);
                dto.setValue(0);
            } else {
                dto.setProductId((int) o[1]);
                dto.setValue((int) o[2]);
            }

            ratingRSDTOList.add(dto);
        }
        return ratingRSDTOList;
    }

    @Override
    public CountRatingProductDTO countStarRatingByUser(int id) {
        CountRatingProductDTO countRatingProductDTO = new CountRatingProductDTO();

        List<Object[]> objects = ratingRepository.countStarRatingByUser(id);

        for (Object[] o : objects) {
            switch ((int) o[0]) {
                case 1:
                    countRatingProductDTO.setTotalStar1(((BigInteger) o[1]).intValue());
                    break;
                case 2:
                    countRatingProductDTO.setTotalStar2(((BigInteger) o[1]).intValue());
                    break;
                case 3:
                    countRatingProductDTO.setTotalStar3(((BigInteger) o[1]).intValue());
                    break;
                case 4:
                    countRatingProductDTO.setTotalStar4(((BigInteger) o[1]).intValue());
                    break;
                case 5:
                    countRatingProductDTO.setTotalStar5(((BigInteger) o[1]).intValue());
                    break;
            }
        }
        return countRatingProductDTO;
    }

    @Override
    public List<Rating> getRatingByUserAndStar(int userId, int star, int page, int pageSize) {
        int pageNew = page < 1 ? 0 : (page - 1) * pageSize;
        return ratingRepository.findRatingByUserAndStar(userId, star, pageNew, pageSize);
    }

    @Override
    public Rating getRatingByProductAndProductOption(int userId, int productId, int productOptionId) {
        return ratingRepository.findRatingByProductAndProductOption(userId, productId, productOptionId);
    }

    @Override
    public void updateReview(int ratingId, InputRatingUpdateDTO inputReview) {
        Rating rating = ratingRepository.findById(ratingId).orElse(null);
        if (rating != null) {
            rating.setComment(inputReview.getComment());
            rating.setIncognito(inputReview.isIncognito());
            rating.setStar(inputReview.getStar());

            Rating ratingSave = ratingRepository.save(rating);

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
            saveImage(ratingSave, imageDataService, inputReview.getListFiles());
//                }
//            }).start();
        }
    }

    @SneakyThrows
    private void saveImage(Rating rating, IImageDataService imageDataService, List<MultipartFile> files) {
        if (files != null) {
            Set<ImageData> imageData = new HashSet<>(rating.getDataImages());

            if (files.size() > 0) {
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
            }else{
                rating.setDataImages(null);
            }
            ratingRepository.save(rating);
            imageData.forEach(v -> {
                imageDataService.removeImageById(v.getId());
            });
        }
    }
}
