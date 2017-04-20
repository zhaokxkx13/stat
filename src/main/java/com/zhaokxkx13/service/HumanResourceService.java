package com.zhaokxkx13.service;

import com.zhaokxkx13.Bean.EmployeeFlow;

import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/20.
 */
public interface HumanResourceService {
    Map<String, Long> getEachCompanyEmployeeNum();

    EmployeeFlow getEmployeeFlow();
}
