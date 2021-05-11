package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IRatingController;
import com.khangse616.serverfashionrs.mappers.impl.RatingDTOMapper;
import com.khangse616.serverfashionrs.models.Rating;
import com.khangse616.serverfashionrs.models.RatingStar;
import com.khangse616.serverfashionrs.models.dto.CountRatingProductDTO;
import com.khangse616.serverfashionrs.models.dto.RatingProductDTO;
import com.khangse616.serverfashionrs.services.IRatingService;
import com.khangse616.serverfashionrs.services.IRatingStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class RatingController implements IRatingController {
    @Autowired
    private IRatingService ratingService;

    @Override
    public ResponseEntity<String> autoRating() {
        ratingService.autoRating();
        return ResponseEntity.ok().body("done");
    }

    @Override
    public ResponseEntity<RatingProductDTO> getRatingByProduct(int id, String select, int page) {
        RatingProductDTO ratingProductDTO = new RatingProductDTO();

        CountRatingProductDTO countRatingProductDTO = ratingService.countRatingByStarOfProduct(id);
        countRatingProductDTO.setTotalImage(ratingService.countRatingByImageOfProduct(id));

        ratingProductDTO.setTotalCount(countRatingProductDTO);

        List<Rating> ratings;
        if (select.equals("all"))
            ratings = ratingService.getAllRatingByProductId(id, (page - 1) * 10);
        else {
            if (!select.equals("image")) {
                ratings = ratingService.getRatingByProductIdAndStar(id, Integer.parseInt(select), (page - 1) * 10);
            } else {
                ratings = null;
            }
        }

        assert ratings != null;
        ratingProductDTO.setData(ratings.stream()
                .map(value -> new RatingDTOMapper().mapRow(value)).collect(Collectors.toList()));
        return ResponseEntity.ok().body(ratingProductDTO);
    }
}