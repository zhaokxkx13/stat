package com.zhaokxkx13.service;

import com.zhaokxkx13.Bean.*;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
public interface CustomerService {
    List<CustomerConsume> getTopNCustomerConsum(Date startDate, Date endDate, int n);

    List<DepartmentSellKpi> getDepartmentSellKpi(Date startDate, Date endDate, int n);

    List<OrderDetails> getOrderDetails(String name);

    List<ProductDetails> getProductDetails(String name);

    CustomerPurchase getCustomerPurchase(String companyName);

    AreaSellDetails getAreaSellDetails(Date startDate, Date endDate);

    List<MonthPredict> getMonthPredict();
}
