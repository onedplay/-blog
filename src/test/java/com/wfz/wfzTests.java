package com.wfz;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.wfz.dao.mapper.ArticleMapper;
import com.wfz.dao.pojo.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//1、进行单元测试的方法不能有返回值，否则会报 No test were found
//2、进行单元测试的方法不能私有化
//3、junit版本问题
@SpringBootTest
public class wfzTests {

    @Autowired
    private ArticleMapper articleMapper;




    @Test
    public void test1(){

//        Article article = new Article();
//        article.setAuthorId(3l);
//        article.setBodyId(20l);
//        article.setCategoryId(2l);
//        article.setCommentCounts(20);
//        article.setCreateDate(1621947720727l);
//        article.setId(13l);
//        article.setSummary("mybatisplus学习专用，测试13");
//        article.setWeight(0);
//        article.setTitle("测试学习13");
//        article.setViewCounts(30);
//        articleMapper.insert(article);

//        ArrayList<Long> objects = new ArrayList<>();
//        objects.add(1405916999732707332l);
//        objects.add(1405916999732707333l);
//        objects.add(1405916999732707334l);
//        articleMapper.selectBatchIds(objects);
//        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("Title","测试学习");
//        articleMapper.deleteByMap(objectObjectHashMap);

//        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        HashMap<SFunction<Article,?>, Object> map = new HashMap<>();
        map.put(Article::getId,1405916999732707336L);
        queryWrapper.allEq(map);
        articleMapper.selectList(queryWrapper);
    }
}
