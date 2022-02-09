package com.wfz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wfz.dao.pojo.Article;
import com.wfz.test.MsMetric;
import com.wfz.vo.ArticleVo;
import com.wfz.vo.Result;
import com.wfz.vo.params.ArticleParam;
import com.wfz.vo.params.PageParams;



public interface ArticleService extends IService<Article> {

    @MsMetric
    Result listArticle(PageParams pageParams);

    Result hotArticle(int limit);

    Result newArticle(int limit);

    Result listArchives();

    ArticleVo findArticleById(Long articleId);

    Result publish(ArticleParam articleParam);

    ArticleVo testFirst();
}
