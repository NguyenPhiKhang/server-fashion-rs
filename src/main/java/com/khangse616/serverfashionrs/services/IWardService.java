package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Ward;

import java.util.List;

public interface IWardService {
    List<Ward> getWardByDistrict(int provinceId, int districtId);
}
