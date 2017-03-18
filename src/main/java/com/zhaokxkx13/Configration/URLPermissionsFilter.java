package com.zhaokxkx13.Configration;

import com.zhaokxkx13.dao.entity.Permission;
import com.zhaokxkx13.dao.entity.Role;
import com.zhaokxkx13.dao.entity.User;
import com.zhaokxkx13.dao.inf.RoleMapper;
import com.zhaokxkx13.dao.inf.UserMapper;
import com.zhaokxkx13.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaokxkx13 on 2017/3/17.
 */
@Component("urlPermissionsFilter")
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {

    @Autowired
    UserService userService;

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        String curUrl = getRequstUrl(request);
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null
                || StringUtils.endsWithAny(curUrl, ".js", ".css", ".html")
                || StringUtils.endsWithAny(curUrl, ".jpg", ".png", ".gif", ".jpeg")
                || StringUtils.equals(curUrl, "/unauthor")) {
            return true;

        }
        Set<String> urls = userService.getUserPermissionByUserName(((User) subject.getPrincipal()).getUsername());
        return urls.contains(curUrl);
    }

    private String getRequstUrl(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String queryString = httpServletRequest.getQueryString();
        queryString = StringUtils.isBlank(queryString) ? "" : "?" + queryString;
        return httpServletRequest.getRequestURI() + queryString;
    }
}
