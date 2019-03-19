package com.springboot.cache.controller;


import com.springboot.cache.dto.Result;
import com.springboot.cache.entity.User;
import com.springboot.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping()
    public Result findAll(){
        return new Result(true,"查询成功",userService.findAll());
    }

    @GetMapping("/{userId}")
    public Result findAll(@PathVariable Integer userId){
        return new Result(true,"查询成功",userService.findByUserId(userId));
    }

    @PostMapping()
    public Result save(@RequestBody User user){
        user.setCreateTime(new Date());
        user=userService.save(user);
        return new Result(true,"保存成功","新增ID"+user.getUserId());
    }

    @PutMapping("/{userId}")
    public Result update(@RequestBody User user,@PathVariable Integer userId){
        user.setUserId(userId);
        userService.Update(user);
        return new Result(true,"保存成功");
    }

    @DeleteMapping("/{userId}")
    public Result delete(@PathVariable Integer userId){
        userService.delete(userId);
        return new Result(true,"删除成功");
    }
}
