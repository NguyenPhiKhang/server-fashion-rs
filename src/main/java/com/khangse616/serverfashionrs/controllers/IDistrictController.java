package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.District;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/default")
public interface IDistrictController{
    @GetMapping("/province/{provinceId}/get-district")
    List<District> getDistrictByProvince( @PathVariable int provinceId);
}
