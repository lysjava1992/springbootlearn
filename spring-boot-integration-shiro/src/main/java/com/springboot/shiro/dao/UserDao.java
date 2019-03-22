package com.springboot.shiro.dao;

import com.springboot.shiro.entity.User;

import java.util.List;

public interface UserDao {
     User findByUsername(String username) ;
     List<User> find();
}
