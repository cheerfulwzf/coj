package com.cheerful.oj.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheerful.oj.platform.dao.ReplyDiscussDao;
import com.cheerful.oj.platform.entity.ReplyDiscuss;
import com.cheerful.oj.platform.service.ReplyDiscussService;
import org.springframework.stereotype.Service;

/**
 * (ReplyDiscuss)表服务实现类
 *
 * @author makejava
 * @since 2022-03-10 17:04:22
 */
@Service("replyDiscussService")
public class ReplyDiscussServiceImpl extends ServiceImpl<ReplyDiscussDao, ReplyDiscuss> implements ReplyDiscussService {

}

