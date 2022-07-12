package com.cloud.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.cloud.order.mapper")
@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    /*
     Nacos配置中心：
     统一配置管理：
     在生产环境中，随着微服务越来越多，甚至可能达到成百上千的情况，此时，如果服务的配置文件需要变化、修改，从而
     会影响不少的服务，此时会有以下情况：
     1：需要对服务的文件逐个调整，
     2：调整完服务的配置之后，与配置关联的众多服务都需要重启
     在生产环境下，服务重启的影响时挺大的，所以，就有以下的需求：
     1：对服务的配置进行统一的管理，比如只修改一个地方，就可以完成众多服务的修改；
     2：配置改动完之后，受影响的服务不需要重启，配置就能立马生效，实现配置的热更新。
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
