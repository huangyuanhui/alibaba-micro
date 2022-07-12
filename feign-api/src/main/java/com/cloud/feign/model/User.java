package com.cloud.feign.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String address;
}
