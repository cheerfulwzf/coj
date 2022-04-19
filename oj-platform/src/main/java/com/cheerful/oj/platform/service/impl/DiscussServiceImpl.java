package com.cheerful.oj.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheerful.oj.platform.dao.DiscussDao;
import com.cheerful.oj.platform.entity.Discuss;
import com.cheerful.oj.platform.service.DiscussService;
import org.springframework.stereotype.Service;

/**
 * (Discuss)表服务实现类
 *
 * @author makejava
 * @since 2022-03-10 17:04:22
 */
@Service("discussService")
public class DiscussServiceImpl extends ServiceImpl<DiscussDao, Discuss> implements DiscussService {

}

