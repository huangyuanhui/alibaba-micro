package com.cloud.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeignClientConfiguration {

    /*
    Feign的性能优化：
    Feign底层的客户端实现：Feign是一个声明式客户端，只是把我们的声明变成Http请求，最终发Http请求时还是会用到
    别客户端。
    URLConnection：默认实现，性能不好，不支持链接池
    Apache HttpClient：支持连接池
    OKHttp：支持连接池

    因此优化Feign的性能主要包括：
    1：使用链接池代替默认的URLConnection
    2：日志级别最好用basic或者none
     */
    @Bean
    public Logger.Level logLevel() {
        return Logger.Level.BASIC;
    }
}
