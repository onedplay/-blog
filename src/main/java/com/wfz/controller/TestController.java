package com.wfz.controller;

import com.wfz.service.ArticleService;
import com.wfz.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public Result testFirst(){
        return Result.success(articleService.testFirst());
    }


}
