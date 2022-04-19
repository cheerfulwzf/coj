package com.cheerful.oj.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheerful.oj.platform.dao.UserLoginLogDao;
import com.cheerful.oj.platform.entity.UserLoginLog;
import com.cheerful.oj.platform.service.UserLoginLogService;
import org.springframework.stereotype.Service;

/**
 * (UserLoginLog)表服务实现类
 *
 * @author makejava
 * @since 2022-03-10 17:04:24
 */
@Service("userLoginLogService")
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogDao, UserLoginLog> implements UserLoginLogService {

}

