package com.zhaokxkx13.service.impl;

import com.zhaokxkx13.dao.entity.*;
import com.zhaokxkx13.dao.inf.*;
import com.zhaokxkx13.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/30.
 */
@Service
public class ImportServiceImpl implements ImportService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    BalanceMapper balanceMapper;

    @Autowired
    ProfitMapper profitMapper;

    @Autowired
    CashFlowMapper cashFlowMapper;

    @Override
    public int importEmployee(List<Employee> employeeList) {
        int result = employeeMapper.insertAll(employeeList);
        return result;
    }

    @Override
    public int importProduct(List<Product> productList) {
        int result = productMapper.insertAll(productList);
        return result;
    }

    @Override
    public int importCustomer(List<Customer> customerList) {
        int result = customerMapper.insertAll(customerList);
        return result;
    }

    @Override
    public int importOrders(List<Order> orderList) {
        int result = orderMapper.insertAll(orderList);
        return result;
    }

    @Override
    public int importDepartment(List<Department> departmentList) {
        int result = departmentMapper.insertAll(departmentList);
        return result;
    }

    @Override
    public int importBalance(List<Balance> balanceList) {
        int result = balanceMapper.insertAll(balanceList);
        return result;
    }

    @Override
    public int importProfit(List<Profit> profitList) {
        int result = profitMapper.insertAll(profitList);
        return result;
    }

    @Override
    public int importCashFlow(List<CashFlow> cashFlowList) {
        int result = cashFlowMapper.insertAll(cashFlowList);
        return result;
    }
}
