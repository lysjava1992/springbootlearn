package com.springboot.redis.service;


import com.springboot.redis.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByUserId(Integer userId);

    User Update(User user);

    User save(User user);

    void delete(Integer userId);
}
