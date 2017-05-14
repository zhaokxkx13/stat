package com.zhaokxkx13.service;

import com.zhaokxkx13.dao.entity.*;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/30.
 */
public interface ImportService {
    int importEmployee(List<Employee> employeeList);

    int importProduct(List<Product> productList);

    int importCustomer(List<Customer> customerList);

    int importOrders(List<Order> orderList);

    int importDepartment(List<Department> departmentList);

    int importBalance(List<Balance> balanceList);

    int importProfit(List<Profit> profitList);

    int importCashFlow(List<CashFlow> cashFlowList);
}
