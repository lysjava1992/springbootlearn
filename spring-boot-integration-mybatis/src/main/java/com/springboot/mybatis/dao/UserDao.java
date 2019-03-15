package com.springboot.mybatis.dao;

import com.springboot.mybatis.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findByUserId(Integer id);
}
