package com.zhaokxkx13.service;

import com.zhaokxkx13.Bean.CompanyAreaDetails;
import com.zhaokxkx13.Bean.EmployeeFlow;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/20.
 */
public interface HumanResourceService {
    Map<String, Long> getEachCompanyEmployeeNum();

    EmployeeFlow getEmployeeFlow();

    List<CompanyAreaDetails> getCompanyAreaDetails();

    Map<String, Integer> getSexBalance();

    Map<String, Integer> getEducationBalance();

    Map<String, Integer> getClassBalance();

    Map<String, Integer> getAgeBalance();
}
