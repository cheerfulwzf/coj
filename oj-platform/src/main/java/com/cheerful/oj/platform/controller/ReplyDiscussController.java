package com.cheerful.oj.platform.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheerful.oj.platform.entity.ReplyDiscuss;
import com.cheerful.oj.platform.service.ReplyDiscussService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * (ReplyDiscuss)表控制层
 *
 * @author makejava
 * @since 2022-03-10 17:04:22
 */
@RestController
@RequestMapping("replyDiscuss")
public class ReplyDiscussController extends ApiController {

	/**
	 * 服务对象
	 */
	@Resource
	private ReplyDiscussService replyDiscussService;

	/**
	 * 分页查询所有数据
	 *
	 * @param page         分页对象
	 * @param replyDiscuss 查询实体
	 * @return 所有数据
	 */
	@GetMapping
	public R selectAll(Page<ReplyDiscuss> page, ReplyDiscuss replyDiscuss) {
		return success(this.replyDiscussService.page(page, new QueryWrapper<>(replyDiscuss)));
	}

	/**
	 * 通过主键查询单条数据
	 *
	 * @param id 主键
	 * @return 单条数据
	 */
	@GetMapping("{id}")
	public R selectOne(@PathVariable Serializable id) {
		return success(this.replyDiscussService.getById(id));
	}

	/**
	 * 新增数据
	 *
	 * @param replyDiscuss 实体对象
	 * @return 新增结果
	 */
	@PostMapping
	public R insert(@RequestBody ReplyDiscuss replyDiscuss) {
		return success(this.replyDiscussService.save(replyDiscuss));
	}

	/**
	 * 修改数据
	 *
	 * @param replyDiscuss 实体对象
	 * @return 修改结果
	 */
	@PutMapping
	public R update(@RequestBody ReplyDiscuss replyDiscuss) {
		return success(this.replyDiscussService.updateById(replyDiscuss));
	}

	/**
	 * 删除数据
	 *
	 * @param idList 主键结合
	 * @return 删除结果
	 */
	@DeleteMapping
	public R delete(@RequestParam("idList") List<Long> idList) {
		return success(this.replyDiscussService.removeByIds(idList));
	}
}

