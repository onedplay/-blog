package com.wfz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wfz.dao.mapper.ArticleMapper;
import com.wfz.dao.mapper.UserMapper;
import com.wfz.dao.pojo.Article;
import com.wfz.dao.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class wfzTest2 {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test2(){

        List<User> users = userMapper.test2();
        for (User user:users
             ) {
            System.out.println(user);
        }
    }

    @Test
    public void  test3(){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getEmail,User::getPassword);
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user:users
             ) {
            System.out.println(user);
        }
    }
}
