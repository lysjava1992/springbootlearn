package com.springboot.cache.dao;


import com.springboot.cache.entity.User;

import java.util.List;
public interface UserDao {
    List<User> findAll();
    User findByUserId(Integer id);
    void update(User user);
    void save(User user);
    void delete(Integer userId);
}
