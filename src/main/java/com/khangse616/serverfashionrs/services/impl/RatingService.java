package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.Rating;
import com.khangse616.serverfashionrs.models.RatingStar;
import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.models.dto.CountRatingProductDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.AVGRatedProductDTO;
import com.khangse616.serverfashionrs.repositories.ProductRepository;
import com.khangse616.serverfashionrs.repositories.RatingRepository;
import com.khangse616.serverfashionrs.repositories.RatingStarRepository;
import com.khangse616.serverfashionrs.repositories.UserRepository;
import com.khangse616.serverfashionrs.services.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        int size_product = products.size()-100;
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
}
