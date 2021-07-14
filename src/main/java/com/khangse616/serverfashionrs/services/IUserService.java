package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.User;

import java.util.List;

public interface IUserService {
    User getUserById(int userId);
    List<Integer> getListIdUser();
    List<User> getListUserFilter(String search, int active, int page, int pageSize);
    int countListUserFilter(String search, int active);
}
