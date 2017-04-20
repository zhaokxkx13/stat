package com.zhaokxkx13.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.zhaokxkx13.StatApplication.class)
public class FinanceServiceTest {
    @Autowired
    FinanceService financeService;

    @Test
    public void test1() {
        Date endDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        Map<String, String> resultMap = financeService.getDupontDetails(startDate, endDate);
        System.out.println(resultMap);
    }
}
