package com.zhaokxkx13.dao;

import com.zhaokxkx13.StatApplication;
import com.zhaokxkx13.dao.entity.CashFlow;
import com.zhaokxkx13.dao.inf.CashFlowMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatApplication.class)
public class CashFlowDaoTest {

    @Autowired
    CashFlowMapper cashFlowMapper;

    @Test
    public void test1() throws ParseException {
        Map<String, Date> map = new HashMap<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2016-11-11");
        Date endDate = new Date();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        List<CashFlow> list = cashFlowMapper.selectByDate(map);
        for (CashFlow item : list) {
            System.out.println(item);
        }
    }
}
