package com.zhaokxkx13.service.impl;

import com.zhaokxkx13.Bean.CompanyAreaDetails;
import com.zhaokxkx13.Bean.EmployeeFlow;
import com.zhaokxkx13.dao.entity.Employee;
import com.zhaokxkx13.dao.inf.CompanyMapper;
import com.zhaokxkx13.dao.inf.EmployeeMapper;
import com.zhaokxkx13.service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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

    @Override
    public List<CompanyAreaDetails> getCompanyAreaDetails() {
        List<Map> cIdValues = employeeMapper.selectCompanyEmployeeCount();
        List<CompanyAreaDetails> resultList = new ArrayList<>();
        for (Map item : cIdValues) {
            Integer companyId = Integer.parseInt(String.valueOf(item.get("company_id")));
            Integer value = Integer.parseInt(String.valueOf(item.get("count(id)")));
            List<Employee> employeeList = employeeMapper.selectByCompanyId(companyId);
            String companyName = companyMapper.selectById(companyId).getName();
            Double sumSalery = 0.0;
            for (Employee employee : employeeList) {
                sumSalery += employee.getSalery();
            }
            sumSalery /= employeeList.size() == 0 ? 1 : employeeList.size();
            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            CompanyAreaDetails companyAreaDetails = new CompanyAreaDetails();
            companyAreaDetails.setCity(companyName);
            companyAreaDetails.setAverageSalery(decimalFormat.format(sumSalery));
            companyAreaDetails.setCount(value);
            resultList.add(companyAreaDetails);
        }
        return resultList;
    }

    @Override
    public Map<String, Integer> getSexBalance() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        Date startDate = calendar.getTime();
        Map<String, Date> parameterMap = new HashMap<>();
        parameterMap.put("startDate", startDate);
        List<Map> sexMap = employeeMapper.selectSexBalance(parameterMap);
        Map<String, Integer> resultMap = new HashMap<>();
        for (Map item : sexMap) {
            resultMap.put(item.get("sex").toString(), Integer.parseInt(item.get("count(id)").toString()));
        }
        return resultMap;
    }

    @Override
    public Map<String, Integer> getEducationBalance() {
        return null;
    }

    @Override
    public Map<String, Integer> getClassBalance() {
        return null;
    }

    @Override
    public Map<String, Integer> getAgeBalance() {
        return null;
    }
}
