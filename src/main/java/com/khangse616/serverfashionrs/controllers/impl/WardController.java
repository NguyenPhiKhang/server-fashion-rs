package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IWardController;
import com.khangse616.serverfashionrs.models.Ward;
import com.khangse616.serverfashionrs.services.IWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class WardController implements IWardController {
    @Autowired
    private IWardService wardService;

    @Override
    public List<Ward> getWardByDistrict(int districtId) {
        return wardService.getWardByDistrict(districtId);
    }
}
