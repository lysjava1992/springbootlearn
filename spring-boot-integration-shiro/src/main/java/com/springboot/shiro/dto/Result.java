package com.springboot.shiro.dto;

import lombok.Data;

@Data
public class Result {
    private boolean state;
    private Integer stateCode;
    private Object object;

    public Result(boolean state, Integer stateCode, Object object) {
        this.state = state;
        this.stateCode = stateCode;
        this.object = object;
    }

    public Result(boolean state, Integer stateCode) {
        this.state = state;
        this.stateCode = stateCode;
    }
}
