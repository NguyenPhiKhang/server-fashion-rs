package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.OptionProductVarchar;

import java.util.List;

public interface IOptionProductVarcharService {
    OptionProductVarchar findOptionById(int id);
    List<OptionProductVarchar> findOptionByAttributeId(int id);
}
