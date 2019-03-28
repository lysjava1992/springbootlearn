package com.springboot.shiro.controller;

import com.springboot.shiro.dto.Result;
import com.springboot.shiro.entity.UserSession;
import com.springboot.shiro.service.SessionService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/session")
public class SessionController {

     @Autowired
     private SessionService sessionService;
     @GetMapping()
     @ResponseBody
    public Result getSessionInfo(){
        UserSession userSession=sessionService.getOneself();
          return new Result(true,20001,userSession);
     }
    @GetMapping("/findall")
    @ResponseBody
     public Result findAll(){
        List<UserSession> userSession=sessionService.findAll();
         return new Result(true,20001,userSession);
     }

     @GetMapping("/{sessionId}")
     @ResponseBody
     public Result delete(@PathVariable String sessionId){
         sessionService.delete(sessionId);
         return new Result(true,20001,"踢出成功");
     }
}
