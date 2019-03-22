package com.springboot.shiro.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserSession {
    private Serializable id;
    private String host;
    private Long defaultTimeout;
    private Long realTimeout;
    private Date StartTime;
    private Date LastActiveTime;

}
