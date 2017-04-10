package com.zhaokxkx13.service;

import com.zhaokxkx13.Bean.CustomerConsume;
import com.zhaokxkx13.Bean.DepartmentSellKpi;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/4/7.
 */
public interface CustomerService {
    List<CustomerConsume> getTopNCustomerConsum(Date startDate, Date endDate, int n);

    List<DepartmentSellKpi> getDepartmentSellKpi(Date startDate, Date endDate, int n);
}
