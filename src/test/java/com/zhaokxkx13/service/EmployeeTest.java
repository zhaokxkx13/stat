package com.zhaokxkx13.service;

import com.zhaokxkx13.Bean.EmployeeFlow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.zhaokxkx13.StatApplication.class)
public class EmployeeTest {
    @Autowired
    HumanResourceService humanResourceService;

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
}
