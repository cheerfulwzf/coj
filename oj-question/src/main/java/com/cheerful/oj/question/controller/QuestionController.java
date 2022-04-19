package com.cheerful.oj.question.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheerful.oj.common.vo.Result;
import com.cheerful.oj.question.entity.Question;
import com.cheerful.oj.question.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Question)表控制层
 *
 * @authoResult makejava
 * @since 2022-03-12 16:05:22
 */
@RestController
@RequestMapping("question")
public class QuestionController{
    /**
     * 服务对象
     */
    @Resource
    private QuestionService questionService;
    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param question 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result<IPage<Question>> selectAll(Page<Question> page, Question question) {
        return Result.success(this.questionService.pageInfo(page, new QueryWrapper<>(question)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<Question> selectOne(@PathVariable Serializable id) {
        return Result.success(this.questionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param question 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<Boolean> insert(@RequestBody Question question) {
        return Result.success(this.questionService.saveInfo(question));
    }

    /**
     * 修改数据
     *
     * @param question 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<Boolean> update(@RequestBody Question question) {
        return Result.success(this.questionService.updateById(question));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.questionService.remove(idList));
    }
}

