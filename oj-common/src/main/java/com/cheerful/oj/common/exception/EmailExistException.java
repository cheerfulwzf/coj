package com.cheerful.oj.common.exception;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/7 22:34
 * @DESCRIPTION:
 */
public class EmailExistException extends RuntimeException {

  public EmailExistException(String message) {
    super(message);
  }
}
