package com.wfz.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wfz.dao.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    List<User> test2();
}
