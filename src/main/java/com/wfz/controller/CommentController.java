package com.wfz.controller;

import com.wfz.service.CommentService;
import com.wfz.vo.Result;
import com.wfz.vo.params.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/article/{id}")
    public Result comments(@PathVariable("id") Long articleId  ){
        return commentService.commentsByArticleId(articleId);
    }

    @PostMapping("/create/change")
    public Result comment(@RequestBody CommentParam commentParam){
        return commentService.comment(commentParam);
    }
}

