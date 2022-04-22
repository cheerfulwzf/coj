package com.cheerful.oj.platform.pojo.vo;

import com.cheerful.oj.platform.exception.Verify;
import lombok.Data;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/27 14:54
 * @DESCRIPTION:
 */
@Data
public class JudgeTaskVO {
    private Long userId;
    @Verify(name = "题目id")
    private Long qid;
    @Verify(name = "语言模板")
    private Integer orderType;
    @Verify(name = "源代码")
    private String source;
    private String nickname;
}
