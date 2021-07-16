package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Account;
import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.repositories.AccountRepository;
import com.khangse616.serverfashionrs.services.IAccountPermissionService;
import com.khangse616.serverfashionrs.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IAccountPermissionService accountPermissionService;

    @Override
    public Account getAccountByUsernameAndPassword(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Account registerAccount(String password, User user, int role) {
        Account account = new Account();
        account.setUsername(String.valueOf(user.getId()));
        account.setPassword(password);
        account.setActive(true);
        account.setUser(user);
        account.setPermission(accountPermissionService.getAccountById(role));

        return accountRepository.save(account);
    }

    @Override
    public boolean checkPasswordCorrect(int userId, String password) {
        return accountRepository.checkPasswordCorrect(userId, password)>0;
    }

    @Override
    public void updatePassword(int userId, String newPassword) {
        accountRepository.updatePassword(newPassword, userId);
    }
}
