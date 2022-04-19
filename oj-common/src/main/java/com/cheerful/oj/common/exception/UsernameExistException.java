package com.cheerful.oj.common.exception;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/28 12:09
 * @DESCRIPTION:
 */
public class UsernameExistException extends RuntimeException{
    public UsernameExistException(String message) {
        super(message);
    }
}
