package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Address;

import java.util.List;

public interface IAddressService {
    void addAddressForUser(Address address, int userId, int wardId);
    void updateAddressForUser();
    void deleteAddressForUser();
    List<Address> getAddressOfUser();
//    boolean setDefaultIsFalseForUser(int userId);
}
