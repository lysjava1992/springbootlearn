package com.springboot.mybatis.controller;

import com.springboot.mybatis.dao.UserDao;
import com.springboot.mybatis.dto.Result;
import com.springboot.mybatis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;
    @GetMapping()
    public Result findAll(){
        return new Result(true,"查询成功",userDao.findAll());
    }

    @GetMapping("/{userId}")
    public Result findAll(@PathVariable Integer userId){
        return new Result(true,"查询成功",userDao.findByUserId(userId));
    }
}
