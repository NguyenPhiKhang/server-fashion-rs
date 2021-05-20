package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Address;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
public interface IAddressController {
    @PostMapping("/user/{userId}/add-address")
    String addAddressForUser(@PathVariable("userId") int userId, @RequestParam("ward") int wardId, @RequestBody Address address);
}
