package com.springboot.shiro.controller;

import com.springboot.shiro.dto.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/role")
public class RoleTestController {

   @GetMapping()
   @RequiresRoles("admin")
   @ResponseBody
    public Result test1() {
    return  new Result(true,20001,"有权限");
    }
}
