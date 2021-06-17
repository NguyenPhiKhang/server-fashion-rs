package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.OptionProductInteger;

public interface IOptionProductIntegerService {
    OptionProductInteger save(OptionProductInteger optionProductInteger);
    OptionProductInteger getOptionProductIntegerByProductId(int productId);
}
