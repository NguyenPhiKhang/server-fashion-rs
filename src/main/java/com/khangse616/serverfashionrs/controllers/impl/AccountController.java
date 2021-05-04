package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.models.Account;
import com.khangse616.serverfashionrs.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @PostMapping("/account/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        Account accountLogin = accountService.getAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        return ResponseEntity.ok().body(accountLogin);
    }
}
