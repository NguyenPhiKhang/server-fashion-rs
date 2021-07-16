package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Account;
import com.khangse616.serverfashionrs.models.User;

public interface IAccountService {
    Account getAccountByUsernameAndPassword(String username, String password);
    Account registerAccount(String password, User user, int role);
    boolean checkPasswordCorrect(int userId, String password);
    void updatePassword(int userId, String newPassword);
}
