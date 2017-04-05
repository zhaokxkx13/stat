package com.zhaokxkx13.service;

import com.zhaokxkx13.dao.entity.Income;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/3/20.
 */
public interface IncomeService {
    Income getAllIncome();

    Income getCurSumMonthIncome(int month, int year);

    List<Income> getCurMonthIncome(int month, int year);

    Income compareMonth(Income cur, Income pre);

    List<Income> getCurYearIncome(int year);

    List<Income> getYearIncome(Date date,int pageNum,int pageSize);
}
