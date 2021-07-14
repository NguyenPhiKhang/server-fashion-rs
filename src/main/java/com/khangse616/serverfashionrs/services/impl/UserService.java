package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.repositories.UserRepository;
import com.khangse616.serverfashionrs.services.IUserService;
import net.andreinc.mockneat.MockNeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

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

    @Override
    public void autoCreateEmail() {
        List<User> users = userRepository.getListUserFilter("", -1, 0, 1000);

        MockNeat mock = MockNeat.threadLocal();

        users.forEach(user -> {
            String corpEmail;
            do {
                corpEmail = mock.emails().domain("gmail.com").val();
            } while (checkExistEmail(corpEmail));

            user.setEmail(corpEmail);
        });

        userRepository.saveAll(users);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public User registerUser(String name, String email) {
        Random rd = new Random();

        int idUser;
        do {
            idUser = 100000000 + rd.nextInt(2000000000);
        } while (userRepository.existsById(idUser));

        User user = new User();
        user.setId(idUser);
        user.setName(name);
        user.setEmail(email);
        user.setActive(true);
        user.setTimeCreated(new Timestamp(System.currentTimeMillis()));
        user.setTimeUpdated(new Timestamp(System.currentTimeMillis()));

        return userRepository.save(user);
    }
}
