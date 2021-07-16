package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.models.dto.InputUserUpdateDTO;

import java.text.ParseException;
import java.util.List;

public interface IUserService {
    User getUserById(int userId);
    List<Integer> getListIdUser();
    List<User> getListUserFilter(String search, int active, int page, int pageSize);
    int countListUserFilter(String search, int active);
    void autoCreateEmail();
    boolean checkExistEmail(String email);
    User registerUser(String name, String email);
    User updateUser(InputUserUpdateDTO input_user) throws ParseException;
}
