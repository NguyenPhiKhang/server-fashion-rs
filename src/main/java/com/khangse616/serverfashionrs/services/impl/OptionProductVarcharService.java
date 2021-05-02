package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.OptionProductVarchar;
import com.khangse616.serverfashionrs.repositories.OptionProductVarcharRepository;
import com.khangse616.serverfashionrs.services.IOptionProductVarcharService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionProductVarcharService implements IOptionProductVarcharService {
    @Autowired
    private OptionProductVarcharRepository  optionProductVarcharRepository;

    @Override
    public OptionProductVarchar findOptionById(int id) {
        return optionProductVarcharRepository.findById(id);
    }
}
