package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.repositories.UserRepository;
import com.khangse616.serverfashionrs.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(int userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public List<Integer> getListIdUser() {
        return userRepository.getAllIdUser();
    }

    @Override
    public List<User> getListUserFilter(String search, int active, int page, int pageSize) {
        int pageNew = page < 1 ? 0 : (page - 1) * pageSize;
        return userRepository.getListUserFilter(search, active, pageNew, pageSize);
    }

    @Override
    public int countListUserFilter(String search, int active) {
        return userRepository.countListUserFilter(search, active);
    }
}
