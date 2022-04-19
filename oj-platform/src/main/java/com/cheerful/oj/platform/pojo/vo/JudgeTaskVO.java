package com.cheerful.oj.platform.pojo.vo;

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
    private Long qid;
    private Integer orderType;
    private String source;
    private String nickname;
}
