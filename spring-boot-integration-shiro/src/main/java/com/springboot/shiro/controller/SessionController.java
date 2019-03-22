package com.springboot.shiro.controller;

import com.springboot.shiro.dto.Result;
import com.springboot.shiro.entity.UserSession;
import com.springboot.shiro.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
