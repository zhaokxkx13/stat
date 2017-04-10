package com.zhaokxkx13.service.impl;

import com.zhaokxkx13.Bean.CustomerConsume;
import com.zhaokxkx13.Bean.DepartmentSellKpi;
import com.zhaokxkx13.dao.entity.Customer;
import com.zhaokxkx13.dao.entity.DepartmentPlan;
import com.zhaokxkx13.dao.entity.Order;
import com.zhaokxkx13.dao.entity.ProductOrder;
import com.zhaokxkx13.dao.inf.DepartmentPlanMapper;
import com.zhaokxkx13.dao.inf.ProductOrderMapper;
import com.zhaokxkx13.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
@Service
public class CustomerServicceImpl implements CustomerService {
    @Autowired
    ProductOrderMapper productOrderMapper;

    @Autowired
    DepartmentPlanMapper departmentPlanMapper;

    @Override
    public List<CustomerConsume> getTopNCustomerConsum(Date startDate, Date endDate, int n) {
        Map<String, Date> map = new HashMap<>();
        map.put("startDate", startDate);
        if (endDate != null) {
            map.put("endDate", endDate);
        }
        List<ProductOrder> productOrderList = productOrderMapper.selectByDate(map);
        List<CustomerConsume> customerConsumeList = new ArrayList<>();
        Map<String, CustomerConsume> reduceMap = new HashMap<>();
        for (ProductOrder item : productOrderList) {
            String companyName = item.getOrder().getCustomer().getCompany().getName();
            if (reduceMap.get(companyName) != null) {
                reduceMap.get(companyName).setMoneyAmount(reduceMap.get(companyName).getMoneyAmount() + item.getPrice() * item.getNum());
                reduceMap.get(companyName).setNum(reduceMap.get(companyName).getNum() + item.getNum());
            } else {
                CustomerConsume consume = new CustomerConsume();
                Order order = item.getOrder();
                Customer customer = order.getCustomer();
                consume.setCustomer(customer);
                consume.setNum(item.getNum());
                consume.setMoneyAmount(item.getPrice() * item.getNum());
                reduceMap.put(item.getOrder().getCustomer().getCompany().getName(), consume);
            }
        }
        for (Map.Entry<String, CustomerConsume> entry : reduceMap.entrySet()) {
            customerConsumeList.add(entry.getValue());
        }
        Collections.sort(customerConsumeList, new Comparator<CustomerConsume>() {
            @Override
            public int compare(CustomerConsume o1, CustomerConsume o2) {
                return (int) (o1.getMoneyAmount() - o2.getMoneyAmount());
            }
        });
        return customerConsumeList.size() > n ? customerConsumeList.subList(0, n) : customerConsumeList;
    }

    @Override
    public List<DepartmentSellKpi> getDepartmentSellKpi(Date startDate, Date endDate, int n) {
        Map<String, Date> parameterMap = new HashMap<>();
        parameterMap.put("startDate", startDate);
        parameterMap.put("endDate", endDate);
        List<DepartmentPlan> planList = departmentPlanMapper.selectByDate(parameterMap);
        List<ProductOrder> orderList = productOrderMapper.selectByDate(parameterMap);

        Map<String, Double> planMap = new HashMap<>();
        for (DepartmentPlan item : planList) {
            String departmentName = item.getDepartment().getName();
            planMap.put(departmentName, item.getPlanSell());
        }

        Map<String, Double> actMap = new HashMap<>();
        for (ProductOrder item : orderList) {
            String departmentName = item.getOrder().getEmployee().getDepartment().getName();
            Double actSell = item.getPrice() * item.getNum();
            if (!actMap.containsKey(departmentName)) {
                actMap.put(departmentName, actSell);
            } else {
                actMap.put(departmentName, actMap.get(departmentName) + actSell);
            }
        }

        List<DepartmentSellKpi> resultList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : actMap.entrySet()) {
            if (planMap.get(entry.getKey()) == null)
                continue;
            DepartmentSellKpi departmentSellKpi = new DepartmentSellKpi();
            departmentSellKpi.setDepartmentName(entry.getKey());
            departmentSellKpi.setActSell(entry.getValue());
            departmentSellKpi.setPlanSell(planMap.get(entry.getKey()) == null ? 0.0 : planMap.get(entry.getKey()));
            departmentSellKpi.setPersent(departmentSellKpi.getPlanSell() == 0 ? 0 : departmentSellKpi.getActSell() / departmentSellKpi.getPlanSell() * 100);
            resultList.add(departmentSellKpi);
        }

        resultList.sort(new Comparator<DepartmentSellKpi>() {
            @Override
            public int compare(DepartmentSellKpi o1, DepartmentSellKpi o2) {
                return -(int) (o1.getPersent() - o2.getPersent());
            }
        });
        return resultList;
    }


}
