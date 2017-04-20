package com.zhaokxkx13.service;

import java.util.Date;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/4/15.
 */
public interface FinanceService {
    Map<String, String> getKpiDetails(Date startDate, Date endDate);

    Map<String, String> getDupontDetails(Date startDate, Date endDate);
}
