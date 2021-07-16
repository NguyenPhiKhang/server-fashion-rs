package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Order;
import com.khangse616.serverfashionrs.models.dto.DetailOrderDTO;
import com.khangse616.serverfashionrs.models.dto.InputOrderDTO;
import com.khangse616.serverfashionrs.models.dto.OrderCardDTO;
import com.khangse616.serverfashionrs.models.dto.OrderManagerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface IOrderController {
    @PostMapping("/order/create-order")
    String createOrder(@RequestBody InputOrderDTO orderInput);

    @GetMapping("/user/{userId}/get-list-order")
    List<OrderCardDTO> getListOrderByStatus(@PathVariable("userId") int userId, @RequestParam(value = "stt", defaultValue = "0") int status);

    @PutMapping("/order/{orderId}/update-status")
    String updateStatusOfOrder(@PathVariable("orderId") int orderId, @RequestParam("stt") int status);

    @GetMapping("/order/{orderId}/detail-order")
    DetailOrderDTO getDetailOrder(@PathVariable int orderId);

    @GetMapping("/orders/{userId}/get-orders-for-admin")
    List<OrderManagerDTO> getOrdersForAdmin(@PathVariable("userId") int userId,
                                            @RequestParam(value = "status", defaultValue = "0") int status,
                                            @RequestParam(value="search_user", defaultValue = "") String searchUser,
                                            @RequestParam(value = "p", defaultValue = "1") int page,
                                            @RequestParam(value = "p_size", defaultValue = "5") int pageSize);

    @GetMapping("/orders/{userId}/count-orders-for-admin")
    int countOrdersForAdmin(@PathVariable("userId") int userId, @RequestParam(value = "status", defaultValue = "0") int status, @RequestParam(value="search_user", defaultValue = "") String searchUser );
}