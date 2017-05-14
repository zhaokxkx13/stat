package com.zhaokxkx13.service;

import com.zhaokxkx13.dao.entity.*;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/29.
 */
public interface DownloadService {
    List<Employee> getAllEmployee();

    List<Customer> getAllCustomer();

    List<Department> getAllDepartment();

    List<Order> getAllOrder();

    List<Product> getAllProduct();

    List<Balance> getAllBalance();

    List<Profit> getAllProfit();

    List<CashFlow> getAllCashFlow();
}
