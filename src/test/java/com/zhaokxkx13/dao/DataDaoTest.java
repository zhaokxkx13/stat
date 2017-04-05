package com.zhaokxkx13.dao;

import com.github.pagehelper.PageHelper;
import com.zhaokxkx13.StatApplication;
import com.zhaokxkx13.dao.entity.Income;
import com.zhaokxkx13.dao.inf.IncomeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/3/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatApplication.class)
public class DataDaoTest {
    @Autowired
    IncomeMapper incomeMapper;

    @Test
    public void test1() {
        PageHelper.startPage(1,10);
        List<Income> incomeList = incomeMapper.selectAllIncome();
        for(Income item:incomeList){
            System.out.println(item);
        }
    }
}
