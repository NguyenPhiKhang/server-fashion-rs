package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.Account;
import com.khangse616.serverfashionrs.models.dto.AccountDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

public class AccountDTOMapper implements RowMapper<AccountDTO, Account> {
    @Override
    public AccountDTO mapRow(Account account) {
        try{
            return new AccountDTO(account.getUsername(), account.getPassword(), account.isActive(), account.getPermission(), new UserDTOMapper().mapRow(account.getUser()));
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public AccountDTO mapRow(Account account, IImageDataService repository) {
        return null;
    }
}
