package com.cloud.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
Nacos配置管理：
微服务启动时会从Nacos读取多个配置文件：
1：[spring.application.name]-[spring.profiles.active].yaml，例如：user-service-dev.yaml
2：[spring.application.name].yaml，例如：user-service.yaml
无论profile如何变化，[spring.application.name].yaml这个文件是一定会加载的，因此多环境共享配置可以写入这个文件！

多种配置的优先级：
（nacos中当前环境配置）服务名-profile.yaml  > （nacos中共享配置）服务名.yaml > 本地配置
 */
@Component
@ConfigurationProperties(prefix = "pattern")
@Data
public class PatternProperties {

    private String dateformat;

    private String envShareValue;

    private String name;
}
