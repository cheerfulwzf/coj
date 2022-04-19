package com.cheerful.oj.platform.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheerful.oj.platform.entity.Discuss;
import com.cheerful.oj.platform.service.DiscussService;
import com.cheerful.oj.platform.util.IpUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * (Discuss)表控制层
 *
 * @author makejava
 * @since 2022-03-10 17:04:20
 */
@RestController
@RequestMapping("discuss")
public class DiscussController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private DiscussService discussService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param discuss 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Discuss> page, Discuss discuss) {
        return success(this.discussService.page(page, new QueryWrapper<>(discuss)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.discussService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param discuss 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Discuss discuss) {
        return success(this.discussService.save(discuss));
    }

    /**
     * 修改数据
     *
     * @param discuss 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Discuss discuss) {
        return success(this.discussService.updateById(discuss));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.discussService.removeByIds(idList));
    }

    @GetMapping("/getIp")
    public String getIp(HttpServletRequest request) throws Exception {
        return IpUtil.getIp(request);
    }
}

