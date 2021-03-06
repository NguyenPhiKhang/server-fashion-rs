package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.Account;
import com.khangse616.serverfashionrs.models.dto.AccountDTO;
import com.khangse616.serverfashionrs.models.dto.AccountRegisterDTO;
import com.khangse616.serverfashionrs.models.dto.InputChangePassword;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface IAccountController{
    @PostMapping("/account/login")
    ResponseEntity<AccountDTO> login(@RequestBody Account account);

    @PostMapping("/account/register")
    ResponseEntity<ResponseMessage<AccountDTO>> register(@RequestBody AccountRegisterDTO account, @RequestParam(value = "role", defaultValue = "2") int role);

    @PutMapping("/account/{userId}/change-password")
    ResponseEntity<ResponseMessage<Integer>> changePassword(@PathVariable("userId") int userId, @RequestBody InputChangePassword input);
}
