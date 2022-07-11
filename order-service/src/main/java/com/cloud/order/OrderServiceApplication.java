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
     Nacos注册中心原理 ：
     服务提供者再启动的时候会把自己的信息注册到注册中心，服务消费者会定时向注册中心拉取服务列表，拉取到服务
     列表之后发起远程调用。
     Nacos对比Eureka的优点是：当服务列表发生变更时，Nacos会主动推送服务列表，即PULL与PUSH结合，
     服务列表更新及时、效率更高。

     nacos中，服务分为临时实例 与 非临时实例，默认情况下，实例是非临时实例
     1：临时实例的健康检测是：临时实例会主动定期向注册中心发送心跳，实现服务的健康检测，当一段时间临时实例没有心跳上来，Nacos
     会把临时实例从服务列表中剔除：舔狗
     2：非临时实例的健康检测则是：由Nacos主动去发请求询问非临时实例的健康状态，即使非临时实例挂掉了，
     Nacos也不会把非临时实例在服务列表中剔除：非舔狗

     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
