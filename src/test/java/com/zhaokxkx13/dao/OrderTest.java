package com.zhaokxkx13.dao;

import com.zhaokxkx13.StatApplication;
import com.zhaokxkx13.dao.entity.Order;
import com.zhaokxkx13.dao.inf.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhaokxkx13 on 2017/4/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatApplication.class)
public class OrderTest {
    @Autowired
    OrderMapper orderMapper;

    @Test
    public void test1() {
        List<Integer> customerIdList = new ArrayList<>();
        customerIdList.add(1);
        List<Order> orderList = orderMapper.selectByCustomerId(customerIdList);
        for (Order item : orderList) {
            System.out.println(item);
        }
    }

    @Test
    public void test2() throws ParseException {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date old = df.parse("2016-11-01");
        Map<String, Date> map = new HashMap<>();
        map.put("startDate", old);
        map.put("endDate", date);
        List<Order> orderList = orderMapper.selectByDate(map);
        for (Order order : orderList) {
            System.out.println(order);
        }
    }
}
