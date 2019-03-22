package com.springboot.shiro.controller;

import com.springboot.shiro.dao.UserDao;
import com.springboot.shiro.util.AuthImgUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("authimg")
    public void getAuthImg(HttpServletResponse response) throws IOException {
        new AuthImgUtils().service(response);
    }
    @GetMapping("login")
    public String login(){

        return "login";
    }
    @PostMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("code") String code,
                        Map<String,Object> map){
        if(code==null||("").equals(code)){
            map.put("msg","验证码为空");
            return "login";
        }
        String createCode= (String) SecurityUtils.getSubject().getSession().getAttribute("rand");
        if(!code.toLowerCase().equals(createCode.toLowerCase())){
            map.put("msg","验证码错误");
            return "login";
        }
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            SecurityUtils.getSubject().login(token);
        }catch (UnknownAccountException e){
            map.put("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            map.put("msg","密码错误");
            return "login";
        }
        return "index";
    }

    @GetMapping("index")
    public String index(){
        return "index";
    }

}
