package com.cloud.order.controller;

import com.cloud.feign.clients.UserClient;
import com.cloud.order.model.Order;
import com.cloud.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/orders")
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private UserClient userClient;

    @GetMapping("/{id}")
    public Order getById(@PathVariable("id") Long id, @RequestHeader(required = false, value = "Comment") String comment) {
        System.out.println("comment = " + comment);
        Order order = orderService.getById(id);
        order.setUser(userClient.getById(order.getUserId()));
        return order;
    }
}
