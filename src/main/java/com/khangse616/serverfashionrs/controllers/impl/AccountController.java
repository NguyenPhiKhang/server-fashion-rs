package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IAccountController;
import com.khangse616.serverfashionrs.mappers.impl.AccountDTOMapper;
import com.khangse616.serverfashionrs.models.Account;
import com.khangse616.serverfashionrs.models.dto.AccountDTO;
import com.khangse616.serverfashionrs.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountController implements IAccountController {
    @Autowired
    private IAccountService accountService;

    @Override
    public ResponseEntity<AccountDTO> login(Account account) {
        AccountDTO accountLogin = new AccountDTOMapper().mapRow(accountService.getAccountByUsernameAndPassword(account.getUsername(), account.getPassword()));
        return ResponseEntity.ok().body(accountLogin);
    }
}
