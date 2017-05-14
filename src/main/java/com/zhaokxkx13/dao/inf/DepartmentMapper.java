package com.zhaokxkx13.dao.inf;

import com.zhaokxkx13.dao.entity.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
@Mapper
public interface DepartmentMapper {
    List<Department> selectAll();

    int insertAll(List<Department> departmentList);
}
