package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Account;

public interface IAccountService {
    Account getAccountByUsernameAndPassword(String username, String password);
}
