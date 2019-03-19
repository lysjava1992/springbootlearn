package com.springboot.cache.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer userId;
    private String  userName;
    private Integer userAge;
    private String  address;
    private String  phone;
    private Date    createTime;
}
