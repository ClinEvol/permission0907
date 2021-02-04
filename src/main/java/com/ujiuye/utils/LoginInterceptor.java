package com.ujiuye.utils;

import com.ujiuye.pojo.Employee;
import com.ujiuye.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private EmployeeService employeeService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        String[] openUrl={"auth","login","css","fonts","images","img","js"};
        for (String open : openUrl) {
            if(requestURI.contains(open)){
                return true;//放行
            }
        }
        //找session看有没有登录
        Object loginSession = request.getSession().getAttribute("LOGIN_SESSION");
        if(loginSession!=null){//登录
            return true;//放行
        }
        //没有登录，找cookie
        Cookie[] cookies = request.getCookies();
        String username=null;
        if(cookies!=null && cookies.length>0){//看一下浏览器有没有cookie
            //找一下有没有我们要 的数据
            for (Cookie cookie : cookies) {
                if("username".equals(cookie.getName())){
                    username=cookie.getValue();//找到我们要的数据
                    break;
                }
            }
        }
        if(username!=null){//找没username   重新登录
            Employee employee = employeeService.getByUserName(username);
            //重新存入session
            request.getSession().setAttribute("LOGIN_SESSION",employee);
            return true;//放行
        }
        //cookie没有找到
        response.sendRedirect("/login.html");
        return false;
    }
}
