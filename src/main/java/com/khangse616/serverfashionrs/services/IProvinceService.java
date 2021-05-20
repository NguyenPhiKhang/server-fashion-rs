package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IProvinceService{
    List<Province> getAllProvince();
}
