package com.cheerful.oj.platform.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheerful.oj.common.vo.Result;
import com.cheerful.oj.platform.entity.Submission;
import com.cheerful.oj.platform.service.SubmissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Submission)表控制层
 *
 * @author makejava
 * @since 2022-03-10 17:04:23
 */
@RestController
@RequestMapping("submission")
public class SubmissionController {
    /**
     * 服务对象
     */
    @Resource
    private SubmissionService submissionService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param submission 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<IPage<Submission>> selectAll(Page<Submission> page, Submission submission) {
        return Result.success(this.submissionService.page(page, new QueryWrapper<>(submission)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<Submission> selectOne(@PathVariable Serializable id) {
        return Result.success(this.submissionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param submission 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody Submission submission) {
        return Result.success(this.submissionService.save(submission));
    }

    /**
     * 修改数据
     *
     * @param submission 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody Submission submission) {
        return Result.success(this.submissionService.updateById(submission));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.submissionService.removeByIds(idList));
    }
}

