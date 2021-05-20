package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.Ward;
import com.khangse616.serverfashionrs.repositories.WardRepository;
import com.khangse616.serverfashionrs.services.IWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardService implements IWardService {
    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<Ward> getWardByDistrict(int districtId) {
        return wardRepository.findWardByDistrictIdOrderByName(districtId);
    }

    @Override
    public Ward getWardById(int wardId) {
        return wardRepository.findById(wardId).orElse(null);
    }
}
