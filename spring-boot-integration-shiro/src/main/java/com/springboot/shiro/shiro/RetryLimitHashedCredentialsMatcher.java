package com.springboot.shiro.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 登陆次数管理 限制时间与session的缓存时间一般不同 所以直接使用redis的模板实现
 * HashedCredentialsMatcher是密码验证的匹配值
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static  String LOGIN_LOCK="login_lock_";

    private static  String LOGIN_COUNT="login_count_";

    private int maxCount=5;

    public RetryLimitHashedCredentialsMatcher(int maxCount) {
        this.maxCount = maxCount;
    }
    public RetryLimitHashedCredentialsMatcher() {

    }

    /**
     * 重写验证方法
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username= (String) token.getPrincipal();
        //访问一次在缓存中记录一次
        stringRedisTemplate.opsForValue().increment(LOGIN_COUNT+username,1);
        //获取当前次数
        int realCount=Integer.parseInt(stringRedisTemplate.opsForValue().get(LOGIN_COUNT+username));
        if(realCount>=(maxCount+1)){//超出次数
            stringRedisTemplate.opsForValue().set(LOGIN_LOCK+username,"LOCK");
            stringRedisTemplate.expire(LOGIN_LOCK+username,1, TimeUnit.HOURS);//设置锁定时间
        }else {
            //剩余次数
            SecurityUtils.getSubject().getSession().setAttribute("remain",(maxCount-realCount));
        }
        if("LOCK".equals(stringRedisTemplate.opsForValue().get(LOGIN_LOCK+username))){
            //被锁 抛异常
            throw new ExcessiveAttemptsException();
        }
        //登录验证
        boolean matches=super.doCredentialsMatch(token, info);
        if(matches){//匹配成功 登录次数清零
            stringRedisTemplate.opsForValue().set(LOGIN_COUNT+username,"0");
        }
        return  matches;
    }
}











