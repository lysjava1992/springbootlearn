package com.springboot.shiro.service.impl;

import com.springboot.shiro.entity.UserSession;
import com.springboot.shiro.service.SessionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {
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
}
