package com.khangse616.serverfashionrs.controllers.impl;


import com.khangse616.serverfashionrs.controllers.IProvinceController;
import com.khangse616.serverfashionrs.models.Province;
import com.khangse616.serverfashionrs.services.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProvinceController implements IProvinceController {
    @Autowired
    private IProvinceService provinceService;

    @Override
    public List<Province> getAllProvince() {
        return provinceService.getAllProvince();
    }
}
