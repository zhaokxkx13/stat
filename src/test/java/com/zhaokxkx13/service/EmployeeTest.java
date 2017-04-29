package com.zhaokxkx13.service;

import com.zhaokxkx13.Bean.EmployeeFlow;
import com.zhaokxkx13.dao.entity.Employee;
import com.zhaokxkx13.dao.inf.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.zhaokxkx13.StatApplication.class)
public class EmployeeTest {
    @Autowired
    HumanResourceService humanResourceService;

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void test1() {
        Map<String, Long> resultMap = humanResourceService.getEachCompanyEmployeeNum();
        for (Map.Entry<String, Long> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    @Test
    public void test2() {
        EmployeeFlow result = humanResourceService.getEmployeeFlow();
        System.out.println(result);
    }

    @Test
    public void test3() {
        List<Employee> employeeList = employeeMapper.selectByCompanyId(1);
        System.out.println(employeeList.get(1));
    }

    @Test
    public void test4() {
        Map<String, Integer> resultMap = humanResourceService.getSexBalance();
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    @Test
    public void test5() {
        Map<String, Integer> resultMap = humanResourceService.getEducationBalance();
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    @Test
    public void test6() {
        Map<String, Integer> resultMap = humanResourceService.getEducationBalance();
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    @Test
    public void test7() {
        Map<String, Integer> resultMap = humanResourceService.getClassBalance();
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    @Test
    public void test8() {
        Map<String, Integer> resultMap = humanResourceService.getAgeBalance();
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
