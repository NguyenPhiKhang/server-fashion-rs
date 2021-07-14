package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.AccountPermission;
import com.khangse616.serverfashionrs.repositories.AccountPermissionRepository;
import com.khangse616.serverfashionrs.services.IAccountPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountPermissionService implements IAccountPermissionService {
    @Autowired
    private AccountPermissionRepository accountPermissionRepository;

    @Override
    public AccountPermission getAccountById(int id) {
        return accountPermissionRepository.findById(id).get();
    }
}
