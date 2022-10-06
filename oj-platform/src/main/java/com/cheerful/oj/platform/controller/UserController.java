package com.cheerful.oj.platform.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheerful.oj.common.vo.Result;
import com.cheerful.oj.platform.entity.User;
import com.cheerful.oj.platform.service.UserService;
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
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-03-10 17:04:23
 */
@RestController
@RequestMapping("user")
public class UserController {

	/**
	 * 服务对象
	 */
	@Resource
	private UserService userService;

	/**
	 * 分页查询所有数据
	 *
	 * @param page 分页对象
	 * @param user 查询实体
	 * @return 所有数据
	 */
	@GetMapping
	public Result<IPage<User>> selectAll(Page<User> page, User user) {
		return Result.success(this.userService.page(page, new QueryWrapper<>(user)));
	}

	/**
	 * 通过主键查询单条数据
	 *
	 * @param id 主键
	 * @return 单条数据
	 */
	@GetMapping("{id}")
	public Result<User> selectOne(@PathVariable Serializable id) {
		return Result.success(this.userService.getById(id));
	}

	/**
	 * 新增数据
	 *
	 * @param user 实体对象
	 * @return 新增结果
	 */
	@PostMapping
	public Result<Boolean> insert(@RequestBody User user) {
		return Result.success(this.userService.save(user));
	}

	/**
	 * 修改数据
	 *
	 * @param user 实体对象
	 * @return 修改结果
	 */
	@PutMapping
	public Result<Boolean> update(@RequestBody User user) {
		return Result.success(this.userService.updateById(user));
	}

	/**
	 * 删除数据
	 *
	 * @param idList 主键结合
	 * @return 删除结果
	 */
	@DeleteMapping
	public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
		return Result.success(this.userService.removeByIds(idList));
	}
}

