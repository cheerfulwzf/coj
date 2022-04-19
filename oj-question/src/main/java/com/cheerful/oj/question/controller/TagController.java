package com.cheerful.oj.question.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheerful.oj.common.vo.Result;
import com.cheerful.oj.question.service.TagService;
import com.cheerful.oj.question.entity.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Tag)表控制层
 *
 * @authoResult makejava
 * @since 2022-03-12 16:05:22
 */
@RestController
@RequestMapping("tag")
public class TagController{
    /**
     * 服务对象
     */
    @Resource
    private TagService tagService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param tag 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<Tag> page, Tag tag) {
        return Result.success(this.tagService.page(page, new QueryWrapper<>(tag)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<Tag> selectOne(@PathVariable Serializable id) {
        return Result.success(this.tagService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param tag 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody Tag tag) {
        return Result.success(this.tagService.save(tag));
    }

    /**
     * 修改数据
     *
     * @param tag 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody Tag tag) {
        return Result.success(this.tagService.updateById(tag));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.tagService.removeByIds(idList));
    }

    @GetMapping("/query/{query}")
    public Result<List<Tag>> query(@PathVariable String query){
        return Result.success(this.tagService.list(new QueryWrapper<Tag>().like("tag_name",query)));
    }
}

