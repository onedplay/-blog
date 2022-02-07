package com.wfz.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfz.common.aop.LogAnnotation;
import com.wfz.common.cache.Cache;
import com.wfz.dao.pojo.Article;
import com.wfz.service.ArticleService;
import com.wfz.vo.ArticleVo;
import com.wfz.vo.Result;
import com.wfz.vo.params.ArticleParam;
import com.wfz.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    //加上此注解 代表要对此接口记录日志
    @LogAnnotation(module="文章",operation="获取文章列表")
    @PostMapping
    public Result listArticle(@RequestBody PageParams pageParams){


        return articleService.listArticle(pageParams);
    }

    @PostMapping("/hot")
    @Cache(expire = 5 * 60 * 1000,name = "hot_article")
    public Result hotArticle(){
        int limit=5;
        return articleService.hotArticle(limit);
    }

    @PostMapping("/new")
    public Result newArticle(){
        int limit=5;
        return articleService.newArticle(limit);
    }

    @PostMapping("/listArchives")
    public Result listArchives(){

        return articleService.listArchives();
    }

    @PostMapping("/view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){
        ArticleVo articleVo = articleService.findArticleById(articleId);
        return Result.success(articleVo);
    }
    @PostMapping("/publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }

}
