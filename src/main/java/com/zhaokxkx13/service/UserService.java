package com.zhaokxkx13.service;

import com.zhaokxkx13.dao.entity.Role;
import com.zhaokxkx13.dao.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by zhaokxkx13 on 2017/3/20.
 */
public interface UserService {
    User getUserByName(String userName);

    Set<String> getUserPermissionByUserName(String userName);

    Set<String> getUserRoleByUsername(String username);

    void register(User user);

    List<User> selectAll();

    void deleteUserRole(Integer userId, Integer roleId);

    void clearAuthorizationInfo(String username);

    User getById(Integer id);

    List<Role> getUnauthRole(String username);

    void addUserRole(Integer userId, Integer roleId);
}
