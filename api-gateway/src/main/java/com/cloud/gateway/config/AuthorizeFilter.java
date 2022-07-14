package com.cloud.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/*
过滤器执行顺序：
请求进入网关会碰到三个过滤器：当前路由的过滤器、DefaultFilter、全局过滤器GlobalFilter
请求路由后（因为当前路由的过滤器只有路由确认之后才知道有哪些过滤器生效，因为不同路由有不同的当前路由过滤器），会将当前路由过滤器和DefaultFilter、GlobalFilter，
合并到一个过滤器链（集合）中，排序后依次执行配个过滤器！

问题：过滤器执行顺序怎么排序？
1：首先，每一个过滤器都必须指定一个int类型的order值，order值越小，优先级越高，执行顺序越靠前；
2：GlobalFilter通过实现Ordered接口，或者添加@Order注解来指定order值，由我们自己指定
3：路由过滤器和DefaultFilter的order由Spring指定，默认是按照声明的顺序从1递增
4：当过滤器的order值一样时，会按照defaultFilter > 路由过滤器 > GlobalFilter的顺序执行！

总结：路由过滤器、defaultFilter、全局过滤器的执行顺序？
order值越小，优先级越高；
当order值一样时，顺序是defaultFilter最先，然后是局部的路由过滤器，最后是全局过滤器！
 */
/**
 * 登录验证过滤器
 */
// @Order(-1)  //值越小优先级越高， 与 实现Ordered接口 实现的功能一样
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authorization = exchange.getRequest().getQueryParams().getFirst("authorization");
        if ("admin".equals(authorization)) {
            // 放行
            return chain.filter(exchange);
        }
        // 拦截
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        // 值越小优先级越高
        return -1;
    }
}
