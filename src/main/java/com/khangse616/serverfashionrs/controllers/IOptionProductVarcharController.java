package com.khangse616.serverfashionrs.controllers;


import com.khangse616.serverfashionrs.models.OptionProductVarchar;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface IOptionProductVarcharController {
    @GetMapping("/option-varchar/{optionId}/get-by-attr")
    List<OptionProductVarchar> getOptionByAttributeId(@PathVariable int optionId);
}
