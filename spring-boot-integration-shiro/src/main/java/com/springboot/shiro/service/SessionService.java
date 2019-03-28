package com.springboot.shiro.service;

import com.springboot.shiro.entity.UserSession;

import java.util.List;

public interface SessionService {
    UserSession getOneself();

    List<UserSession> findAll();

    void delete(String sessionId);
}
