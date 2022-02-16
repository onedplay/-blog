package com.wfz.service.impl;

import com.alibaba.fastjson.JSON;


import org.apache.commons.lang3.StringUtils;
import com.wfz.dao.pojo.SysUser;
import com.wfz.service.LoginService;
import com.wfz.service.SysUserService;
import com.wfz.utils.JWTUtils;
import com.wfz.vo.ErrorCode;
import com.wfz.vo.Result;
import com.wfz.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Printable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    private static final String slat = "wfz!@#";
    @Autowired
    @Lazy
    private SysUserService sysUserService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public Result login(LoginParam loginParam) {
        /*
        * 1、检查参数是否合法
        * 2、根据用户名和密码去user表中查询 是否存在
        * 3、如果不存在 登入失败
        * 4、如果存在，使用jwt生成token，返回给前端
        * 5、token放入redis当中，redis token：user信息 设置过期时间
        * （登入认证的时候，先认证token字符串是否合法，去redis认证是否存在）
        * */
        String account=loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        password= DigestUtils.md5Hex(password+slat);
        SysUser sysUser=sysUserService.findUser(account,password);
        if(sysUser==null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        String token = JWTUtils.createToken(sysUser.getId());

        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public SysUser checkToken(String token) {
        if (StringUtils.isBlank(token)){
            return null;
        }

        Map<String, Object> map = JWTUtils.checkToken(token);
        if (map==null){
            return null;
        }

        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if(StringUtils.isBlank(userJson)){
            return null;
        }


        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return  sysUser;

    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }

    @Override
    public Result register(LoginParam loginParam) {
        /*
        * 1、判断参数是否合法
        * 2、判断账户是否存在，存在 返回账户已经被注册
        * 3、不存在 注册新用户
        * 4、生成token
        * 5、存入redis 并返回
        * 6、注意 加上事务，一旦中间的任何过程出现问题，注册的用户 需要回滚
        * */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password) || StringUtils.isBlank(nickname)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser = sysUserService.findUserByAccount(account);
        if (sysUser!=null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password+slat));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(1); //1 为true
        sysUser.setDeleted(0); // 0 为false
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        this.sysUserService.saved(sysUser);

        String token = JWTUtils.createToken(sysUser.getId());

        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("admin"+slat));
    }
}
