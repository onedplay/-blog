package com.wfz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wfz.dao.pojo.SysUser;
import com.wfz.vo.Result;
import com.wfz.vo.UserVo;

public interface SysUserService extends IService<SysUser> {

    UserVo findUserVoById(Long id);

    SysUser findUserById(Long id);



    SysUser findUser(String account, String password);

    Result findUserByToken(String token);

    SysUser findUserByAccount(String account);

    void saved(SysUser sysUser);
}
