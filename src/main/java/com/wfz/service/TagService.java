package com.wfz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wfz.dao.pojo.Tag;
import com.wfz.vo.Result;
import com.wfz.vo.TagVo;

import java.util.List;

public interface TagService extends IService<Tag> {
    List<TagVo> findTagsByArticleId(Long articleId);

    Result hots(int limit);

    Result findAll();

    Result findAllDetail();

    Result findDetailById(Long id);
}
