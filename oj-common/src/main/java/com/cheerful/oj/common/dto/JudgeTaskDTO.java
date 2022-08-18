package com.cheerful.oj.common.dto;

import java.util.List;
import lombok.Data;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/10 21:32
 * @DESCRIPTION: 用户提交后，封装提交后的数据的实体类
 */
@Data
public class JudgeTaskDTO {

  /**
   * 以下属性从下往上分别为 题目id、判题id、输入、输出、时间限制、空间限制、语言选择模板、源代码
   */
  private Long qid;

  private Long submissionId;

  private List<String> input;

  private List<String> output;

  private Double timeLimit;

  private Double memoryLimit;

  /**
   * 语言选择模板。 0--->Java
   */
  private Integer orderType;

  private String source;
}
