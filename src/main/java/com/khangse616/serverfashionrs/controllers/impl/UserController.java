package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IUserController;
import com.khangse616.serverfashionrs.mappers.impl.CardMyRatingDTOMapper;
import com.khangse616.serverfashionrs.mappers.impl.UserDTOMapper;
import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.models.dto.CardMyRatingDTO;
import com.khangse616.serverfashionrs.models.dto.CountRatingProductDTO;
import com.khangse616.serverfashionrs.models.dto.InputUserUpdateDTO;
import com.khangse616.serverfashionrs.models.dto.UserDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;
import com.khangse616.serverfashionrs.services.IRatingService;
import com.khangse616.serverfashionrs.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1/")
public class UserController implements IUserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRatingService ratingService;

    @Autowired
    private IImageDataService imageDataService;

    @Override
    public List<UserDTO> getListUserFilter(String search, int active, int page, int pageSize) {
        return userService.getListUserFilter(search, active, page, pageSize).stream().map(v -> new UserDTOMapper().mapRow(v)).collect(Collectors.toList());
    }

    @Override
    public int countUsersFilter(String search, int active) {
        return userService.countListUserFilter(search, active);
    }

    @Override
    public String createEmailAuto() {
        userService.autoCreateEmail();
        return "done";
    }

    @Override
    public UserDTO getDetailUserById(int userId) {
        return new UserDTOMapper().mapRow(userService.getUserById(userId));
    }

    @Override
    public UserDTO updateUser(InputUserUpdateDTO input_user) throws ParseException {
        return new UserDTOMapper().mapRow(userService.updateUser(input_user));
    }

    @Override
    public List<CardMyRatingDTO> getRatingByUser(int userId, int star, int page, int pageSize) {
        return ratingService.getRatingByUserAndStar(userId, star, page, pageSize).stream().map(v->
                new CardMyRatingDTOMapper().mapRow(v, imageDataService)).collect(Collectors.toList());
    }

    @Override
    public CountRatingProductDTO countStarRatingByUser(int userId) {
        return ratingService.countStarRatingByUser(userId);
    }
}
