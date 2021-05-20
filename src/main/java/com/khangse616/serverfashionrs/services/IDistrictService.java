package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.District;

import java.util.List;

public interface IDistrictService {
    List<District> getDistrictByProvince(int province_id);
}
