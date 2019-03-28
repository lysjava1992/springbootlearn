package com.springboot.shiro.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserSession {
    private Serializable id;
    private String username;
    private String host;
    private Long defaultTimeout;
    private Long realTimeout;
    private Date StartTime;
    private Date LastActiveTime;
    private  boolean check;
    private String state="正常";
}
