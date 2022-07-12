package com.cloud.order.controller;

import com.cloud.order.clients.UserClient;
import com.cloud.order.model.Order;
import com.cloud.order.model.User;
import com.cloud.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/orders")
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    @GetMapping("/{id}")
    public Order getById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);

        // restTemplate
        // String url = "http://user-service/users/" + order.getUserId();
        // User user = restTemplate.getForObject(url, User.class);

        // feign
        User user = userClient.getById(order.getUserId());

        order.setUser(user);
        return order;
    }
}
