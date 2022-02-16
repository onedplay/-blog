package com.wfz.test;

import com.alibaba.fastjson.JSON;
import com.wfz.dao.pojo.SysUser;
import com.wfz.service.LoginService;
import com.wfz.vo.ErrorCode;
import com.wfz.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class Test1 implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        String token = request.getHeader("Authorization");
        log.info("===================request start=================================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}",token);
        log.info("===========================request end=============================");

        if (token==null){
            Result result=Result.fail(ErrorCode.NO_LOGIN.getCode(), "为登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return  false;
        }

        SysUser sysUser = loginService.checkToken(token);
        if (sysUser==null){
            Result result=Result.fail((ErrorCode.NO_LOGIN.getCode()),"未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return  false;
        }
        return true;
    }
}
