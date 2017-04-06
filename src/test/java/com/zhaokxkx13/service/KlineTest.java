package com.zhaokxkx13.service;

import com.zhaokxkx13.dao.entity.Kline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.zhaokxkx13.StatApplication.class)
public class KlineTest {
    @Autowired
    KlineService klineService;

    @Test
    public void test() {
        Date date = new Date();
        List<Kline> result = klineService.getKlineByYear(date);
        for (Kline item : result) {
            System.out.println(item);
        }
    }
}
