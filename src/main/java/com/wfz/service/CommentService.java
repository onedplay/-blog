package com.wfz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wfz.dao.pojo.Comment;
import com.wfz.vo.Result;
import com.wfz.vo.params.CommentParam;

public interface CommentService extends IService<Comment> {
    public Result commentsByArticleId(Long articleId);

    Result comment(CommentParam commentParam);
}
