package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/default")
public interface IAccountController{
    @PostMapping("/account/login")
    ResponseEntity<Account> login(@RequestBody Account account);
}
