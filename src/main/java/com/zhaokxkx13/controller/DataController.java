package com.zhaokxkx13.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhaokxkx13.Bean.*;
import com.zhaokxkx13.dao.entity.Income;
import com.zhaokxkx13.dao.entity.Kline;
import com.zhaokxkx13.dao.entity.Role;
import com.zhaokxkx13.dao.entity.User;
import com.zhaokxkx13.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/3/20.
 */
@RestController
public class DataController {

    @Autowired
    UserService userService;
    @Autowired
    IncomeService incomeService;

    @Autowired
    KlineService klineService;

    @Autowired
    CustomerService customerService;

    @Autowired
    FinanceService financeService;

    @Autowired
    HumanResourceService humanResourceService;

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


    @RequestMapping("/top/consumers")
    public String getTopConsumers() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);

        Date endDate = new Date();
        Date startDate = calendar.getTime();

        List<CustomerConsume> customerConsumeList = customerService.getTopNCustomerConsum(startDate, endDate, 10);
        Gson gson = new Gson();
        return gson.toJson(customerConsumeList);
    }

    @RequestMapping("/department/kpi")
    public String getDepartmentSellKpi(ModelMap modelMap) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);

        Date endDate = new Date();
        Date startDate = calendar.getTime();
        List<DepartmentSellKpi> sellKpis = customerService.getDepartmentSellKpi(startDate, endDate, 1);

        Gson gson = new Gson();
        return gson.toJson(sellKpis);
    }

    @RequestMapping("/order/details")
    public String getOrderDetails(@RequestParam String name) {
        List<OrderDetails> detailsList = customerService.getOrderDetails(name == null ? "beef" : name);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(detailsList);
        jsonStr = "{ \"data\":" + jsonStr + "}";
        return jsonStr;
    }

    @RequestMapping("/product/details")
    public String getProductDetails(@RequestParam String name) {
        List<ProductDetails> detailsList = customerService.getProductDetails(name == null ? "beef" : name);
        Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
        String jsonStr = gson.toJson(detailsList);
        jsonStr = "{ \"data\":" + jsonStr + "}";
        return jsonStr;
    }

    @RequestMapping("/customer/details")
    public String getCustomerDetails() {
        CustomerPurchase purchase = customerService.getCustomerPurchase("IBM");
        Gson gson = new Gson();
        String jsonStr = gson.toJson(purchase);
        return jsonStr;
    }

    @RequestMapping("/area/details")
    public String getAreaDetails() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -6);
        Date startDate = calendar.getTime();
        Date endDate = new Date();
        AreaSellDetails areaSellDetails = customerService.getAreaSellDetails(startDate, endDate);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(areaSellDetails);
        return jsonStr;
    }

    @RequestMapping("/month/predict")
    public String getMonthPredict() {
        List<MonthPredict> monthPredict = customerService.getMonthPredict();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(monthPredict);
        return jsonStr;
    }

    @RequestMapping("/finance/kpiData")
    public String getFinanceKpi() {
        Date endDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        Map<String, String> resultMap = financeService.getKpiDetails(startDate, endDate);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(resultMap);
        return jsonStr;
    }

    @RequestMapping("/humanResource/kpi/employeeNum")
    public String gethumanResourceKpiEmployeeNum() {
        Map<String, Long> resultMap = humanResourceService.getEachCompanyEmployeeNum();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(resultMap);
        return jsonStr;
    }

    @RequestMapping("/humanResource/kpi/employeeFlow")
    public String getEmployeeFlow() {
        EmployeeFlow result = humanResourceService.getEmployeeFlow();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(result);
        return jsonStr;
    }

    @RequestMapping("/humanResource/kpi/companyAreaDetails")
    public String getCompanyAreaDetails() {
        List<CompanyAreaDetails> companyAreaDetailsList = humanResourceService.getCompanyAreaDetails();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(companyAreaDetailsList);
        jsonStr = "{ \"data\":" + jsonStr + "}";
        return jsonStr;
    }

    @RequestMapping("/humanResource/base/sexBalance")
    public String getSexBalance() {
        Map<String, Integer> sexBalance = humanResourceService.getSexBalance();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(sexBalance);
        return jsonStr;
    }

    @RequestMapping("/humanResource/base/ageBalance")
    public String getAgeBalance() {
        Map<String, Integer> ageBalance = humanResourceService.getAgeBalance();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(ageBalance);
        return jsonStr;
    }

    @RequestMapping("/humanResource/base/rankBalance")
    public String getRankBalance() {
        Map<String, Integer> rankBalance = humanResourceService.getClassBalance();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(rankBalance);
        return jsonStr;
    }

    @RequestMapping("/humanResource/base/educationBalance")
    public String getEducationBalance() {
        Map<String, Integer> educationBalance = humanResourceService.getEducationBalance();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(educationBalance);
        return jsonStr;
    }

    @RequestMapping("/permission/userRoleInfo")
    public String getUserRoleInfo(@RequestParam String username) {
        User user = userService.getUserByName(username);
        List<Role> roleList = user.getRoleList();
        Integer id = user.getId();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(roleList);
        jsonStr = "{\"userId\":" + id + ", \"roleList\":" + jsonStr + "}";
        return jsonStr;
    }

    @RequestMapping("/permission/userRoleUnauthInfo")
    public String getUserRoleUnauthInfo(@RequestParam String username) {
        User user = userService.getUserByName(username);
        List<Role> roleList = userService.getUnauthRole(username);
        Integer id = user.getId();
        Gson gson = new Gson();
        String jsonStr = gson.toJson(roleList);
        jsonStr = "{\"userId\":" + id + ", \"roleList\":" + jsonStr + "}";
        return jsonStr;
    }
}
