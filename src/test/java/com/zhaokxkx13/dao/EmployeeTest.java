package com.zhaokxkx13.dao;

import com.zhaokxkx13.StatApplication;
import com.zhaokxkx13.dao.entity.Employee;
import com.zhaokxkx13.dao.inf.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatApplication.class)
public class EmployeeTest {
    @Autowired
    EmployeeMapper employeeMapper;

    //    @Test
    public void test1() {
        List<Employee> employeeList = employeeMapper.selectByName("zhaokxkx13");
        for (Employee item : employeeList) {
            System.out.println(item);
        }
    }

    @Test
    public void test2() {
        List<Map> resultMap = employeeMapper.selectCompanyEmployeeCount();
        for (Map<String, Long> item : resultMap) {
            System.out.println(item.get("company_id") + " " + item.get("count(id)"));
        }
    }

    @Test
    public void test3() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        Map<String, Date> parameterMap = new HashMap<>();
        parameterMap.put("startDate", calendar.getTime());
        List<Map<String, Integer>> resultMap = employeeMapper.selectEmployeeFlow(parameterMap);
        for (Map<String, Integer> item : resultMap) {
            System.out.println(item.get("id"));
        }
    }
}
