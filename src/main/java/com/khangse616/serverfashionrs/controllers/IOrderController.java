package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.dto.DetailOrderDTO;
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

    @PutMapping("/order/{orderId}/update-status")
    String updateStatusOfOrder(@PathVariable("orderId") int orderId, @RequestParam("stt") int status);

    @GetMapping("/order/{orderId}/detail-order")
    DetailOrderDTO getDetailOrder(@PathVariable int orderId);
}
