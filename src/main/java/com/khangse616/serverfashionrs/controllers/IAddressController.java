package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Address;
import com.khangse616.serverfashionrs.models.dto.AddressDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface IAddressController {
    @PostMapping("/user/{userId}/add-address")
    String addAddressForUser(@PathVariable("userId") int userId, @RequestParam("ward") int wardId, @RequestBody Address address);

    @GetMapping("/user/{userId}/get-addresses")
    List<AddressDTO> getAddressesOfUser(@PathVariable("userId") int userId);

    @DeleteMapping("/address/{addressId}/delete")
    String removeAddressById(@PathVariable("addressId") int addressId);

    @PutMapping("/address/update")
    String updateAddress(@RequestBody Address address);
}
