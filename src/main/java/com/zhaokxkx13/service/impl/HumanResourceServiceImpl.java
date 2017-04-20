package com.zhaokxkx13.service.impl;

import com.zhaokxkx13.Bean.EmployeeFlow;
import com.zhaokxkx13.dao.inf.CompanyMapper;
import com.zhaokxkx13.dao.inf.EmployeeMapper;
import com.zhaokxkx13.service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhaokxkx13 on 2017/4/20.
 */
@Service
public class HumanResourceServiceImpl implements HumanResourceService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    CompanyMapper companyMapper;

    @Override
    public Map<String, Long> getEachCompanyEmployeeNum() {
        List<Map> cIdValues = employeeMapper.selectCompanyEmployeeCount();
        Map<String, Long> resultMap = new HashMap<>();
        for (Map item : cIdValues) {
            Integer companyId = Integer.parseInt(String.valueOf(item.get("company_id")));
            Integer value = Integer.parseInt(String.valueOf(item.get("count(id)")));
            String companyName = companyMapper.selectById(companyId).getName();
            resultMap.put(companyName, value.longValue());
        }
        return resultMap;
    }

    @Override
    public EmployeeFlow getEmployeeFlow() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        Map<Integer, Integer> increase = new HashMap<>();
        Map<Integer, Integer> decrease = new HashMap<>();
        Map<Integer, Integer> pureIncrease = new HashMap<>();
        for (int i = 1; i <= month; i++) {
            increase.put(i, 0);
            decrease.put(i, 0);
            pureIncrease.put(i, 0);
        }
        Map<String, Date> parameterMap = new HashMap<>();
        parameterMap.put("startDate", calendar.getTime());
        List<Map<String, Integer>> resultMap = employeeMapper.selectEmployeeFlow(parameterMap);
        for (Map<String, Integer> item : resultMap) {
            Integer leave_month = item.get("month(leave_date)");
            if (leave_month != null) {
                decrease.put(leave_month, decrease.get(leave_month) + 1);
            }
            Integer hire_month = item.get("month(hire_date)");
            if (leave_month != null) {
                increase.put(hire_month, increase.get(hire_month) + 1);
            }
        }
        for (int i = 1; i < month; i++) {
            pureIncrease.put(i, increase.get(i) - decrease.get(i));
        }
        EmployeeFlow result = new EmployeeFlow();
        result.setDecrease(decrease);
        result.setIncrease(increase);
        result.setPureIncrease(pureIncrease);
        return result;
    }
}
