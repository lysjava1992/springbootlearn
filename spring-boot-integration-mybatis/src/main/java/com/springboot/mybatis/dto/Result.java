package com.springboot.mybatis.dto;

import lombok.Data;

@Data
public class Result {
    private boolean state;
    private String message;
    private Object data;

    public Result(boolean state, String message) {
        this.state = state;
        this.message = message;
    }

    public Result(boolean state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }
}
