package com.zhaokxkx13.service;

import com.google.gson.Gson;
import com.zhaokxkx13.Bean.CustomerConsume;
import com.zhaokxkx13.Bean.DepartmentSellKpi;
import com.zhaokxkx13.Bean.OrderDetails;
import com.zhaokxkx13.Bean.ProductDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.zhaokxkx13.StatApplication.class)
public class CustomServiceTest {
    @Autowired
    CustomerService customerService;
//    @Test
    public void test1() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date date = df.parse("2016-12-03");
        List<CustomerConsume> list = customerService.getTopNCustomerConsum(date,null,10);
        for(CustomerConsume item:list){
            System.out.println(item);
        }
    }

//    @Test
    public void test2() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse("2016-12-03");
        Date date1= df.parse("2017-04-10");
        List<DepartmentSellKpi> sellKpiList= customerService.getDepartmentSellKpi(date,date1,1);
        for(DepartmentSellKpi item:sellKpiList){
            System.out.print(item);
        }
    }

    @Test
    public void test3(){
        List<OrderDetails> detailss = customerService.getOrderDetails("beef");
//        for(OrderDetails item :detailss){
//            System.out.println(item);
//        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(detailss));
    }


    @Test
    public void test4(){
        List<ProductDetails> productDetailss = customerService.getProductDetails("beef");
    }
}
