package com.zhaokxkx13.service;


import com.zhaokxkx13.dao.entity.Permission;
import com.zhaokxkx13.dao.entity.Role;
import com.zhaokxkx13.dao.entity.User;
import com.zhaokxkx13.dao.inf.RoleMapper;
import com.zhaokxkx13.dao.inf.UserMapper;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhaokxkx13 on 2017/3/16.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    public User getUserByName(String userName) {
        User user;
        try {
            user = userMapper.selectRoleByUserName(userName);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<String> getUserPermissionByUserName(String userName) {
        User user = getUserByName(userName);
        try {
            List<Role> roleList = user.getRoleList();
            List<Permission> permissionList = new ArrayList<>();
            Set<String> result = new HashSet<>();
            for (Role item : roleList) {
                Role role = roleMapper.selectRolePermissionById(item.getId());
                if (role != null && role.getPermissionList() != null) {
                    permissionList.addAll(role.getPermissionList());
                }
            }
            for (Permission item : permissionList) {
                result.add(item.getUrl());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void register(User user) {
        Date date = new Date();
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        user.setSalt(salt);
        user.setCreateDate(date);
        SimpleHash hash = new SimpleHash("md5", user.getPassword(), user.getCredentialsSalt(), 2);
        String encodedPassword = hash.toHex();
        user.setPassword(encodedPassword);
        userMapper.insert(user);
    }
}
