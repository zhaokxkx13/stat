package com.zhaokxkx13.controller;

import com.github.pagehelper.PageInfo;
import com.zhaokxkx13.Bean.CustomerConsume;
import com.zhaokxkx13.dao.entity.Income;
import com.zhaokxkx13.dao.entity.User;
import com.zhaokxkx13.service.CustomerService;
import com.zhaokxkx13.service.FinanceService;
import com.zhaokxkx13.service.IncomeService;
import com.zhaokxkx13.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaokxkx13 on 2017/3/15.
 */
@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    IncomeService incomeService;

    @Autowired
    CustomerService customerService;

    @Autowired
    FinanceService financeService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, RedirectAttributes redirect, HttpSession session) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordToken upt = new UsernamePasswordToken(username, password);
        session.setAttribute("username", username);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(upt);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            redirect.addFlashAttribute("errorText", "您的账号或密码输入错误!");
            return "redirect:/login";
        }
        return "redirect:/index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView loginGet() {
        return new ModelAndView("login");
    }

    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index";
    }

    @RequestMapping(path = "register", method = RequestMethod.POST)
    public String register(HttpServletRequest request) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String uid = request.getParameter("uid");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUid(uid);
        userService.register(user);
        return "redirect:/login";
    }

    @RequestMapping(path = "register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(path = "index")
    @RequiresPermissions("/index")
    public String index(ModelMap modelMap) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        Income incomeTotal = incomeService.getAllIncome();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        calendar.add(Calendar.MONTH, -1);
        int lastMonth = calendar.get(Calendar.MONTH) + 1;


        Income incomeMonthTotal = incomeService.getCurSumMonthIncome(month, year);
        List<Income> incomeList = incomeService.getCurMonthIncome(month, year);
        Income incomeLastMonthTotal = incomeService.getCurSumMonthIncome(lastMonth, year);
        Income incomeCompared = incomeService.compareMonth(incomeMonthTotal, incomeLastMonthTotal);

        modelMap.addAttribute("comparedMonthTotal", incomeCompared);
        modelMap.addAttribute("incomeTotal", incomeTotal);
        modelMap.addAttribute("incomeMonthTotal", incomeMonthTotal);
        modelMap.addAttribute("incomeList", incomeList);
        modelMap.addAttribute("year", year);
        modelMap.addAttribute("month", month);
        modelMap.addAttribute("username", user.getUsername());
        return "index";
    }

    @RequestMapping(path = "/chart", method = RequestMethod.GET)
    public String chars() {
        return "chart";
    }

    @RequestMapping(path = "/tables/{type}", method = RequestMethod.GET)
    public String tables(@RequestParam(required = true) Integer page, @PathVariable String type, ModelMap modelMap) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date date = calendar.getTime();
        List<Income> incomeList = incomeService.getYearIncome(date, page, 5);
        PageInfo<Income> pageInfo = new PageInfo<Income>(incomeList);
        int pageTotal = pageInfo.getPages();
        modelMap.addAttribute("data", incomeList);
        modelMap.addAttribute("pageTotal", pageTotal);
        return "tables";
    }

    @RequestMapping(path = "sell/kpi")
    public String getSellKpi(ModelMap modelMap) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);

        Date endDate = new Date();
        Date startDate = calendar.getTime();

        List<CustomerConsume> customerConsumeList = customerService.getTopNCustomerConsum(startDate, endDate, 10);
        modelMap.put("customerConsumeList", customerConsumeList);
        return "sell/kpi";
    }

    @RequestMapping("/sell/order")
    public String getOrderDetails() {
        return "sell/orderDetails";
    }

    @RequestMapping("/sell/product")
    public String getProductDetails() {
        return "sell/productDetails";
    }

    @RequestMapping("/sell/customer")
    public String getCustomer() {
        return "sell/customerDetails";
    }

    @RequestMapping("sell/net")
    public String getAearDetails() {
        return "sell/net";
    }

    @RequestMapping("/finance/kpi")
    public String getFinanceKpi(ModelMap modelMap) {
        Date endDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        Map<String, String> resultMap = financeService.getKpiDetails(startDate, endDate);
        modelMap.put("mainServiceIncome", resultMap.get("主营业务收入"));
        modelMap.put("mainServiceCost", resultMap.get("主营业务成本"));
        modelMap.put("mainServiceProfit", resultMap.get("主营业务利润"));
        modelMap.put("cashRate", resultMap.get("现金比率"));
        modelMap.put("assetsRate", resultMap.get("资产负债率"));
        modelMap.put("profitRate", resultMap.get("营业利润率"));
        modelMap.put("profitIncreaseRate", resultMap.get("营业利润增长率"));
        modelMap.put("powerRate", resultMap.get("权益净利率"));
        return "finance/kpi";
    }

    @RequestMapping("/finance/dupont")
    public String getDupont(ModelMap modelMap) {
        Date endDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        Map<String, String> resultMap = financeService.getDupontDetails(startDate, endDate);
        modelMap.put("resultMap", resultMap);
        return "finance/dupont";
    }

    @RequestMapping("/humanResource/kpi")
    public String getHumanResourceKpi() {
        return "humanResource/kpi";
    }

    @RequestMapping("/humanResource/base")
    public String getHumanResourceBase() {
        return "humanResource/base";
    }

    @RequestMapping("/download")
    public String getDownload() {
        return "download";
    }
}
