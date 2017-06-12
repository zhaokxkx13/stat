package com.zhaokxkx13.dao.inf;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/5/31.
 */
@Mapper
public interface UserRoleMapper {
    void deleteById(Map<String, Integer> param);

    void insertUserRole(Map<String, Integer> param);
}
