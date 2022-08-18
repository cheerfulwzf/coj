package com.cheerful.oj.judge.exception;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/11 15:00
 * @DESCRIPTION:
 */
public class OrderTypeNotFoundException extends RuntimeException {

  public OrderTypeNotFoundException(String msg) {
    super(msg);
  }
}
