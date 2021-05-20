package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Address;

import java.util.List;

public interface IAddressService {
    void addAddressForUser(Address address, int userId, int wardId);
    void updateAddressForUser();
    String deleteAddressForUser(int addressId);
    List<Address> getAddressOfUser(int userId);
}
