package com.zhaokxkx13.dao.inf;

import com.zhaokxkx13.dao.entity.Income;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/3/19.
 */
@Mapper
public interface IncomeMapper {
    Income selectById(long id);

    List<Income> selectAllIncome();

    List<Income> selectByYear(Date date);
}
