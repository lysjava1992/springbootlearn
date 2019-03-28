package com.springboot.shiro.shiro;

import com.springboot.shiro.dao.UserDao;
import com.springboot.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomRealm extends AuthorizingRealm{

    @Autowired
    private UserDao userDao;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username= (String) SecurityUtils.getSubject().getPrincipal();
        User user=userDao.findByUsername(username);
        SimpleAuthorizationInfo authenticationInfo=new SimpleAuthorizationInfo();
        authenticationInfo.addRole(user.getRole()); //添加角色
       // authenticationInfo.setRoles(Set<String>);  多个角色  非重
       // authenticationInfo.addStringPermission(); 添加权限
       // authenticationInfo.setStringPermissions(Set<String>);多个权限
        return authenticationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user=userDao.findByUsername(username);
        if(user==null){
            throw new UnknownAccountException();//账号不存在
        }
       // SimpleAuthenticationInfo  authenticationInfo=new SimpleAuthenticationInfo(username,user.getPassword(),getName());    //未加密
        SimpleAuthenticationInfo  authenticationInfo=new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
                ByteSource.Util.bytes(user.getUsername()),getName());   // 加密加盐
        return authenticationInfo;
    }
}
