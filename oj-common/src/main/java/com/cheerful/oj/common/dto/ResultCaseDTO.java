package com.cheerful.oj.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/10 21:27
 * @DESCRIPTION: 判题的结果信息类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultCaseDTO {
    private Integer status;

    private Integer timeUsed;

    private Integer memoryUsed;

    private String errorMessage;

    private String input;

    //用户输出
    private String tempOut;

    private String output;

    public ResultCaseDTO(Integer status, Integer timeUsed, Integer memoryUsed, String errorMessage) {
        this.status = status;
        this.timeUsed = timeUsed;
        this.memoryUsed = memoryUsed;
        this.errorMessage = errorMessage;
    }
}
