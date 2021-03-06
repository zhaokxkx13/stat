package com.zhaokxkx13.dao.inf;

import com.zhaokxkx13.dao.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    Role selectRolePermissionByName(String name);

    Role selectRolePermissionById(Integer id);

    List<Role> selectAll();
}