package com.zhaokxkx13.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaokxkx13.dao.entity.Income;
import com.zhaokxkx13.dao.entity.User;
import com.zhaokxkx13.service.IncomeService;
import com.zhaokxkx13.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.*;

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

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, RedirectAttributes redirect) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordToken upt = new UsernamePasswordToken(username, password);
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
        List<Income> incomeList = incomeService.getYearIncome(date,page,5);
        PageInfo<Income> pageInfo = new PageInfo<Income>(incomeList);
        int pageTotal = pageInfo.getPages();
        modelMap.addAttribute("data", incomeList);
        modelMap.addAttribute("pageTotal",pageTotal);
        return "tables";
    }
}
