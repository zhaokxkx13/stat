package com.zhaokxkx13.dao.inf;

import com.zhaokxkx13.dao.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
@Mapper
public interface EmployeeMapper {
    List<Employee> selectByName(String name);

    List<Map> selectCompanyEmployeeCount();

    List<Map<String, Integer>> selectEmployeeFlow(Map<String, Date> map);

    List<Employee> selectByCompanyId(Integer companyId);

    List<Map> selectSexBalance(Map<String, Date> map);
}
