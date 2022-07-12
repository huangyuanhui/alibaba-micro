package com.cloud.order;

import com.cloud.feign.clients.UserClient;
import com.cloud.feign.config.DefaultFeignClientConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(clients = UserClient.class, defaultConfiguration = DefaultFeignClientConfiguration.class)
@MapperScan("com.cloud.order.mapper")
@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
