package com.zhaokxkx13.dao.inf;

import com.zhaokxkx13.dao.entity.DepartmentPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/10.
 */
@Mapper
public interface DepartmentPlanMapper {

    List<DepartmentPlan> selectByDate(Map<String, Date> map);
}
