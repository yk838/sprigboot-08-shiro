package com.yk.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
    @RequestMapping({"/","/index"})
    public String toIndex (Model model) {
        model.addAttribute("messages","hello shiro");
        return "index";
    }
    @RequestMapping("/user/add")
    public String add() {
        return "user/add";
    }
    @RequestMapping("/user/update")
    public String update() {
        return "user/update";
    }
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装前端传来的用户的登录数据，形成令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);//带着令牌执行登录的方法，如果没有异常
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
}
