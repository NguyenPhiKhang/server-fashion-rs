package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IAccountController;
import com.khangse616.serverfashionrs.mappers.impl.AccountDTOMapper;
import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.Account;
import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.models.dto.AccountDTO;
import com.khangse616.serverfashionrs.models.dto.AccountRegisterDTO;
import com.khangse616.serverfashionrs.services.IAccountService;
import com.khangse616.serverfashionrs.services.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private IUserService userService;

    @Override
    public ResponseEntity<AccountDTO> login(Account account) {
        AccountDTO accountLogin = new AccountDTOMapper().mapRow(accountService.getAccountByUsernameAndPassword(account.getUsername(), account.getPassword()));
        return ResponseEntity.ok().body(accountLogin);
    }

    @Override
    public ResponseEntity<ResponseMessage<AccountDTO>> register(AccountRegisterDTO account, int role) {
        if(userService.checkExistEmail(account.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage<>("Email đã được đăng ký!", null));
        }

        User user = userService.registerUser(account.getName(), account.getEmail());

        return ResponseEntity.ok().body(new ResponseMessage<>("Đăng ký thành công", new AccountDTOMapper().mapRow(accountService.registerAccount(account.getPassword(), user, role))));
    }
}
