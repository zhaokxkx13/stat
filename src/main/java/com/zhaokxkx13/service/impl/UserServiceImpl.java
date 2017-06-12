package com.zhaokxkx13.service.impl;


import com.zhaokxkx13.dao.entity.Permission;
import com.zhaokxkx13.dao.entity.Role;
import com.zhaokxkx13.dao.entity.User;
import com.zhaokxkx13.dao.inf.RoleMapper;
import com.zhaokxkx13.dao.inf.UserMapper;
import com.zhaokxkx13.dao.inf.UserRoleMapper;
import com.zhaokxkx13.service.UserService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhaokxkx13 on 2017/3/16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private EhCacheManager shiroCacheManager;

    @Override
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

    @Override
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

    @Override
    public Set<String> getUserRoleByUsername(String username) {
        User user = userMapper.selectRoleByUserName(username);
        List<Role> roleList = user.getRoleList();
        Set<String> roles = new HashSet<>();
        if (roleList != null && roleList.size() > 0) {
            for (Role role : roleList) {
                roles.add(role.getName());
            }
        }
        return roles;
    }

    @Override
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

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public void deleteUserRole(Integer userId, Integer roleId) {
        Map<String, Integer> param = new HashMap<>();
        param.put("userId", userId);
        param.put("roleId", roleId);
        userRoleMapper.deleteById(param);
    }

    @Override
    public void clearAuthorizationInfo(String username) {
        Cache<Object, Object> cache = shiroCacheManager.getCache("com.zhaokxkx13.Configration.UserRealm.authorizationCache");
        User user = userMapper.selectRoleByUserName(username);
        Set<Object> set = cache.keys();
        User user1 = (User) cache.get(user);
        cache.remove(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> getUnauthRole(String username) {
        User user = getUserByName(username);
        List<Role> roleList = user.getRoleList();
        List<Role> allRole = roleMapper.selectAll();
        List<Role> result = new ArrayList<>();
        for (Role temp : allRole) {
            boolean find = false;
            for (Role all : roleList) {
                if (all.getId() == temp.getId()) {
                    find = true;
                    break;
                }
            }
            if (!find) {
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public void addUserRole(Integer userId, Integer roleId) {
        Map<String, Integer> param = new HashMap<>();
        param.put("userId", userId);
        param.put("roleId", roleId);
        userRoleMapper.insertUserRole(param);
    }
}
