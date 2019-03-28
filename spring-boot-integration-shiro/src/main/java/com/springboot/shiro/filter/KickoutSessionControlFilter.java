package com.springboot.shiro.filter;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 迸发人数限制
 */
public class KickoutSessionControlFilter extends AccessControlFilter{

    private String kickoutUrl;//被踢后地址

    private boolean kickoutAfter=false; //踢人顺序；默认踢出先登录的

    private int maxSession=1; //默认一个账号一处登录

    private SessionManager sessionManager;

    private Cache<String,Deque<Serializable>> cache;

    public String getKickoutUrl() {
        return kickoutUrl;
    }

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }


    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }


    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCache(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro_redis_cache");
    }

    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject=getSubject(servletRequest,servletResponse);
        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            return true;
        }
            Session session=subject.getSession();
            String username= (String) subject.getPrincipal();
            Serializable sessionId=session.getId();
            //先从缓存中读
            Deque<Serializable> deque=cache.get(username);
            if(deque==null){
                //缓存中没有就新建
                deque=new LinkedList<Serializable>();
            }
            if(!deque.contains(sessionId)&&session.getAttribute("kickout")==null){
                //对列中没有此sessionId且不是被踢出的成员
                deque.push(sessionId);
                cache.put(username,deque);
            }
            while (deque.size()>maxSession){
                Serializable kickoutSessionId=null;
                if(kickoutAfter){//提后者
                 kickoutSessionId=deque.removeFirst();
                 cache.put(username,deque);
                }else {
                 kickoutSessionId=deque.removeLast();
                 cache.put(username,deque);
                }
                //获取用户session标记被踢
                try{
                    Session kickoutSession=sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                    if(kickoutSession!=null){
                        kickoutSession.setAttribute("kickout",true);
                    }
                }catch (Exception e){ }
            }
        if(session.getAttribute("kickout")!=null){
            try {
                //退出登录
                subject.logout();
            }catch (Exception e){
                e.printStackTrace();
            }
            saveRequest(servletRequest);
            Map<String, String> resultMap = new HashMap<String, String>();
            //判断是不是Ajax请求
            if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) servletRequest).getHeader("X-Requested-With"))) {
                resultMap.put("user_status", "300");
                resultMap.put("message", "您已经在其他地方登录，请重新登录！");
                //输出json串
                out(servletResponse, resultMap);
            }else{
                //重定向
                WebUtils.issueRedirect(servletRequest, servletResponse, kickoutUrl);
            }
            return false;
        }
        return true;
    }

    private void out(ServletResponse servletResponse, Map<String, String> resultMap) {
        try {
            servletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = servletResponse.getWriter();
            out.println(resultMap);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.err.println("KickoutSessionFilter.class 输出JSON异常，可以忽略。");
        }
    }
}













