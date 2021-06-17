package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.OptionProductInteger;
import com.khangse616.serverfashionrs.repositories.OptionProductIntegerRepository;
import com.khangse616.serverfashionrs.services.IOptionProductIntegerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionProductIntegerService implements IOptionProductIntegerService {
    @Autowired
    private OptionProductIntegerRepository optionProductIntegerRepository;

    @Override
    public OptionProductInteger save(OptionProductInteger optionProductInteger) {
        return optionProductIntegerRepository.save(optionProductInteger);
    }

    @Override
    public OptionProductInteger getOptionProductIntegerByProductId(int productId) {
        return optionProductIntegerRepository.findOptionProductIntegerByProductId(productId);
    }
}
