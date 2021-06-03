package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Ward;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/default")
public interface IWardController {
    @GetMapping("/province/district/{districtId}/get-wards")
    List<Ward> getWardByDistrict(@PathVariable("districtId") int districtId);
}