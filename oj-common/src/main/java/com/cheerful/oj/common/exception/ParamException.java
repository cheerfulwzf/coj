package com.cheerful.oj.common.exception;

import lombok.*;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/31 10:45
 * @DESCRIPTION: 参数不完整错误
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParamException extends RuntimeException{
    private int code;
    private String msg;
}
