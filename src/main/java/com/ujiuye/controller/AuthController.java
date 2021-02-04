package com.ujiuye.controller;

import com.ujiuye.pojo.Employee;
import com.ujiuye.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    //登录
    @RequestMapping("/login")
    public String login(String username, String password, Boolean rememberme,
                        HttpSession session, HttpServletResponse response){

        Employee employee = employeeService.login(username, password);
        if(employee==null){//登录失败
            return "fail";
        }
        session.setAttribute("LOGIN_SESSION",employee);

        if(rememberme!=null && rememberme){//记住密码
            Cookie cookie=new Cookie("username",username);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24*7);//7天自动登录
            response.addCookie(cookie);
        }

        return "success";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response){
        //干掉session
        session.setAttribute("LOGIN_SESSION",null);
        //干掉cookie
        Cookie cookie=new Cookie("username","");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "success";
    }
}
