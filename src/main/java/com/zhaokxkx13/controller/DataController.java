package com.zhaokxkx13.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zhaokxkx13.dao.entity.Income;
import com.zhaokxkx13.dao.entity.Kline;
import com.zhaokxkx13.dao.inf.IncomeMapper;
import com.zhaokxkx13.service.IncomeService;
import com.zhaokxkx13.service.KlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaokxkx13 on 2017/3/20.
 */
@RestController
public class DataController {
    @Autowired
    IncomeService incomeService;

    @Autowired
    KlineService klineService;

    @RequestMapping("/indusAll/Year/distribute")
    public String getYearDistribute() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        List<Income> incomeList = incomeService.getCurYearIncome(year);
        Gson gson = new Gson();
        String result = gson.toJson(incomeList);
        return result;
    }

    @RequestMapping("/kline")
    public String getKline(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (year != null) {
            calendar.set(Calendar.YEAR, year);
        }
        if (month != null) {
            calendar.set(Calendar.MONTH, month);
        } else {
            month = calendar.get(Calendar.MONTH);
            month = month - 1;
            calendar.set(Calendar.MONTH, month);
        }
        List<Kline> KlineList = klineService.getKlineByYear(calendar.getTime());
        Gson gson = new Gson();
        String result = gson.toJson(KlineList);
        return result;
    }
}
