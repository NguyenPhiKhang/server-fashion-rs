package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IDistrictController;
import com.khangse616.serverfashionrs.models.District;
import com.khangse616.serverfashionrs.services.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class DistrictController implements IDistrictController {
    @Autowired
    private IDistrictService districtService;

    @Override
    public List<District> getDistrictByProvince(int provinceId) {
        return districtService.getDistrictByProvince(provinceId);
    }
}
