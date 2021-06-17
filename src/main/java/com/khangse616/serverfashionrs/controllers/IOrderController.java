package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.dto.InputOrderDTO;
import com.khangse616.serverfashionrs.models.dto.OrderCardDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
public interface IOrderController {
    @PostMapping("/order/create-order")
    String createOrder(@RequestBody InputOrderDTO orderInput);

    @GetMapping("/user/{userId}/get-list-order")
    List<OrderCardDTO> getListOrderByStatus(@PathVariable("userId") int userId, @RequestParam(value = "stt", defaultValue = "0") int status);
}
