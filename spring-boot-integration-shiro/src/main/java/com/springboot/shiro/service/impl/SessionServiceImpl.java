package com.springboot.shiro.service.impl;

import com.springboot.shiro.entity.UserSession;
import com.springboot.shiro.service.SessionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    RedisSessionDAO redisSessionDAO;
    @Override
    public UserSession getOneself() {
        UserSession userSession=new UserSession();
        Session session=SecurityUtils.getSubject().getSession();
        userSession.setId(session.getId());
        userSession.setHost(session.getHost());
        userSession.setDefaultTimeout(session.getTimeout());
        userSession.setLastActiveTime(session.getLastAccessTime());
        userSession.setStartTime(session.getStartTimestamp());
        session.setTimeout(1000*30);
        userSession.setRealTimeout(session.getTimeout());
        return userSession;
    }

    @Override
    public List<UserSession> findAll() {
         Collection<Session> collection=redisSessionDAO.getActiveSessions();
         List<UserSession> list=new ArrayList<>();
        for (Session session:collection) {
            UserSession us=new UserSession();
            if(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)!=null){
                String username= session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString();//用户名
                Serializable sessionId=session.getId();  //sessionId
                if (username!=null&&sessionId!=null){
                    us.setId(sessionId);
                    us.setUsername(username);
                    us.setCheck((Boolean) session.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY));//是否通过认证
                    us.setHost(session.getHost());//主机ID
                    us.setStartTime(session.getStartTimestamp()); //登录时间
                    us.setLastActiveTime(session.getLastAccessTime()); //最后活跃时间
                    if(session.getAttribute("logout")!=null){
                        us.setState("已踢出");
                    }
                    list.add(us);
                }
            }
        }
        return list;
    }
    @Override
    public void delete(String sessionId) {
        try{
            Session session=redisSessionDAO.readSession(sessionId);
            if(session!=null){
                session.setAttribute("logout",true);
                redisSessionDAO.update(session);
            }
        }catch (Exception e){}

    }
}
