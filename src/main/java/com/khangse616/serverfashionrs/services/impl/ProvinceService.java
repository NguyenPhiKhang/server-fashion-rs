package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Province;
import com.khangse616.serverfashionrs.repositories.ProvinceRepository;
import com.khangse616.serverfashionrs.services.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> getAllProvince() {
        return provinceRepository.findALlOrderByName();
    }
}
