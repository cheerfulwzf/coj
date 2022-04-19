package com.cheerful.oj.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cheerful.oj.platform.entity.User;
import com.cheerful.oj.platform.pojo.dto.GiteeUserInfo;
import com.cheerful.oj.platform.pojo.vo.LoginAccountVo;
import com.cheerful.oj.platform.pojo.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-03-10 17:04:24
 */
public interface UserService extends IService<User> {
    /**
     * 注册用户
     * @param registerVO
     */
    User register(RegisterVO registerVO);

    /**
     * 用户登录接口
     * @param vo 根据 邮箱or用户名 和密码登录
     * @return 用户详细信息
     */
    User login(LoginAccountVo vo);

    /**
     * gitee用户登录，判断如果是第一次用此社交登录账号要先注册，否则直接登录
     * @param userInfo gitee社交账号
     * @return 用户信息
     */
    User login(GiteeUserInfo userInfo);
}

