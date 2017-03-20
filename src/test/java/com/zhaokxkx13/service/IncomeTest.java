package com.zhaokxkx13.service;

import com.zhaokxkx13.dao.entity.Income;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/3/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.zhaokxkx13.StatApplication.class)
public class IncomeTest {
    @Autowired
    IncomeService incomeService;

    @Test
    public void testSelect() {
        List<Income> incomeList = incomeService.getCurMonthIncome(8, 2016);
        for (Income item : incomeList) {
            System.out.println(item);
        }
    }

    @Test
    public void testSum() {
        Income income = incomeService.getCurSumMonthIncome(8, 3);
        System.out.println(income);
    }
}
