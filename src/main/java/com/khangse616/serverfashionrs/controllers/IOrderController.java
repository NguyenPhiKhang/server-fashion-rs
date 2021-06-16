package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.dto.OrderDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
public interface IOrderController {
    @PostMapping("/order/create-order")
    String createOrder(@RequestBody OrderDTO orderInput);

    @GetMapping("/user/{userId}/get-list-order")
    List<OrderDTO> getListOrderByStatus(@PathVariable("userId") int userId, @RequestParam(value = "stt", defaultValue = "0") int status);
}
