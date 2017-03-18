package com.zhaokxkx13.dao.entity;

/**
 * Created by zhaokxkx13 on 2017/3/16.
 */
public class UserRole {
    private User user;
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
