package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.District;
import com.khangse616.serverfashionrs.repositories.DistrictRepository;
import com.khangse616.serverfashionrs.services.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService implements IDistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> getDistrictByProvince(int province_id) {
        return districtRepository.findDistrictByProvinceIdOrderByName(province_id);
    }
}
