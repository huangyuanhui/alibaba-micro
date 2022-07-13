package com.cloud.user.controller;

import com.cloud.user.config.PatternProperties;
import com.cloud.user.model.User;
import com.cloud.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id,
                        @RequestHeader(value = "Truth", required = false) String truth,
                        @RequestHeader(value = "Comment", required = false) String comment) {
        System.out.println("truth = " + truth);
        System.out.println("comment = " + comment);
        return userService.getById(id);
    }

    @Autowired
    private PatternProperties properties;

    /**
     * 统一配置管理
     *
     * @return
     */
    @GetMapping("/now")
    public String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat()));
    }

    /**
     * 多环境共享配置
     *
     * @return
     */
    @GetMapping("/props")
    public Map<String, String> props() {
        Map<String, String> propsMap = new HashMap<>();
        if (StringUtils.hasText(properties.getDateformat())) {
            propsMap.put("dateformat", LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat())));
        } else {
            propsMap.put("deteformat", null);
        }
        propsMap.put("envShareValue", properties.getEnvShareValue());
        propsMap.put("name", properties.getName());
        return propsMap;
    }

}
