package com.zhaokxkx13.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

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
        System.out.println("fuck");
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
        Income income = incomeService.getAllIncome();
        modelMap.addAttribute("income", income);
        modelMap.addAttribute("username", user.getUsername());
        return "index";
    }
}
