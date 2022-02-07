package com.wfz.controller;

import com.wfz.service.TagService;
import com.wfz.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("tags")
@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("hot")
    public Result hotTag(){
        int limit=6;
        return tagService.hots(limit);

    }
    @GetMapping
    public Result findAll(){
        return tagService.findAll();
    }

    @GetMapping("/detail")
    public Result findAllDetail(){
        return tagService.findAllDetail();
    }

    @GetMapping("/detail/{id}")
    public Result findDetailById(@PathVariable("id") Long id){
        return tagService.findDetailById(id);
    }
}
