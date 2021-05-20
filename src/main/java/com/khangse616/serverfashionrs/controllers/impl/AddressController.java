package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IAddressController;
import com.khangse616.serverfashionrs.models.Address;
import com.khangse616.serverfashionrs.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class AddressController implements IAddressController {
    @Autowired
    private IAddressService addressService;

    @Override
    public String addAddressForUser(int userId, int wardId, Address address) {
        addressService.addAddressForUser(address, userId, wardId);
        return "add success";
    }
}
