package com.cloud.order.clients;

import com.cloud.order.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/users/{id}")
    User getById(@PathVariable("id") Long id);
}
