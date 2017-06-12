package com.zhaokxkx13.Configration;

import com.zhaokxkx13.dao.entity.User;
import com.zhaokxkx13.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by zhaokxkx13 on 2017/3/17.
 */
@Component("userRealm")
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = ((User) principalCollection.getPrimaryPrincipal()).getUsername();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> permissions = userService.getUserPermissionByUserName(username);
        info.setStringPermissions(permissions);
        info.setRoles(userService.getUserRoleByUsername(username));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upt = (UsernamePasswordToken) authenticationToken;
        String username = upt.getUsername();
        User user = userService.getUserByName(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        String crednets = user.getCredentialsSalt();
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getCredentialsSalt()));
        return info;
    }

    public void clearCached(String username) {
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        User user = userService.getUserByName(username);
        SimplePrincipalCollection principals = new SimplePrincipalCollection(user, realmName);
        subject.runAs(principals);
        getAuthorizationCache().remove(subject.getPrincipals());
        System.out.println(getAuthorizationCacheName());
        System.out.println(getAuthenticationCacheName());
        System.out.println(getAuthorizationCacheKey(principals));
    }
}
