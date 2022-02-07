package com.wfz.service;

import com.wfz.dao.pojo.SysUser;
import com.wfz.vo.Result;
import com.wfz.vo.params.LoginParam;


public interface LoginService {
    Result login(LoginParam loginParam);

    SysUser checkToken(String token);

    Result logout(String token);

    Result register(LoginParam loginParam);
}
