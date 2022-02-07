package com.wfz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wfz.dao.pojo.Category;
import com.wfz.vo.CategoryVo;
import com.wfz.vo.Result;

import java.util.List;

public interface

CategoryService extends IService<Category> {
    CategoryVo findCategoryById(Long categoryId);

    Result findAll();

    Result findAllDetail();

    Result findAllDetailById(Long id);
}
