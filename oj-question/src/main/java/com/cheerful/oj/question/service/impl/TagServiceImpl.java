package com.cheerful.oj.question.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheerful.oj.question.dao.TagDao;
import com.cheerful.oj.question.entity.Tag;
import com.cheerful.oj.question.service.TagService;
import org.springframework.stereotype.Service;

/**
 * (Tag)表服务实现类
 *
 * @author makejava
 * @since 2022-03-12 16:05:22
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagDao, Tag> implements TagService {

}

