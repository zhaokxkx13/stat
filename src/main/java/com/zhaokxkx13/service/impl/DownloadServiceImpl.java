package com.zhaokxkx13.service.impl;

import com.zhaokxkx13.dao.entity.*;
import com.zhaokxkx13.dao.inf.*;
import com.zhaokxkx13.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/29.
 */
@Service
public class DownloadServiceImpl implements DownloadService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    BalanceMapper balanceMapper;

    @Autowired
    ProfitMapper profitMaper;

    @Autowired
    CashFlowMapper cashFlowMapper;

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = employeeMapper.selectAll();
        return employeeList;
    }

    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> customerList = customerMapper.selectAll();
        return customerList;
    }

    @Override
    public List<Department> getAllDepartment() {
        List<Department> departmentList = departmentMapper.selectAll();
        return departmentList;
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> orderList = orderMapper.selectAll();
        return orderList;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> productList = productMapper.selectAll();
        return productList;
    }

    @Override
    public List<Balance> getAllBalance() {
        List<Balance> balanceList = balanceMapper.selectAll();
        return balanceList;
    }

    @Override
    public List<Profit> getAllProfit() {
        List<Profit> profitList = profitMaper.selectAll();
        return profitList;
    }

    @Override
    public List<CashFlow> getAllCashFlow() {
        List<CashFlow> cashFlowList = cashFlowMapper.selectAll();
        return cashFlowList;
    }
}
