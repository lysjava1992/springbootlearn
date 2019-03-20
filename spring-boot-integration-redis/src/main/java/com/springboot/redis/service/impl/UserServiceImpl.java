package com.springboot.redis.service.impl;

import com.springboot.redis.dao.UserDao;
import com.springboot.redis.entity.User;
import com.springboot.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "uer")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }


    @Override
    @Cacheable(key = "#userId")
    public User findByUserId(Integer userId) {
        return userDao.findByUserId(userId);
    }


    @Override
    @CachePut(key = "#user.userId")
    public User Update(User user) {
        userDao.update(user);
        return user;
    }

    @Override
    public User save(User user) {
        userDao.save(user);
        return user;
    }

    @Override
    @CacheEvict(key = "#userId")
    public void delete(Integer userId) {
        userDao.delete(userId);
    }
}
