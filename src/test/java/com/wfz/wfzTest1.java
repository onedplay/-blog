package com.wfz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfz.dao.mapper.ArticleMapper;
import com.wfz.dao.pojo.Article;
import com.wfz.vo.params.PageParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class wfzTest1 {
    @Autowired
    private ArticleMapper articleMapper;
    @Test
    public void test1(){
        Article article1 = new Article();
        article1.setId(1405564731300831233l);
        article1.setTitle("wufuz");
        HashMap<String, String> map = new HashMap<>();
        map.put("id","1405564731300831233");
        map.put("title","wufuz");


    }
}
