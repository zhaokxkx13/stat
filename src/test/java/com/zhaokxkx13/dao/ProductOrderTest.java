package com.zhaokxkx13.dao;

import com.zhaokxkx13.StatApplication;
import com.zhaokxkx13.dao.entity.Product;
import com.zhaokxkx13.dao.entity.ProductOrder;
import com.zhaokxkx13.dao.inf.ProductMapper;
import com.zhaokxkx13.dao.inf.ProductOrderMapper;
import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StatApplication.class)
public class ProductOrderTest {

    @Autowired
    ProductOrderMapper productOrderMapper;

    @Autowired
    ProductMapper productMapper;

    //    @Test
    public void test1() {
        List<ProductOrder> productOrderList = productOrderMapper.selectById(1);
        for (ProductOrder item : productOrderList) {
            System.out.println(item);
        }
    }


    @Test
    public void test2() {
        Date date = new Date();
        List<Product> products = productMapper.selectByName("beef");
        Date date1 = new Date();
        System.out.println(date1.getTime()-date.getTime());
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
