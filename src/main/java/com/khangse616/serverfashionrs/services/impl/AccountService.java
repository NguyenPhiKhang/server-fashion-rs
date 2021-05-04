package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Account;
import com.khangse616.serverfashionrs.repositories.AccountRepository;
import com.khangse616.serverfashionrs.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccountByUsernameAndPassword(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password);
    }
}
