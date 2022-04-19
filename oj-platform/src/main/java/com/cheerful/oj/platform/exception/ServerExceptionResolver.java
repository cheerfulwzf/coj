package com.cheerful.oj.platform.exception;

import com.cheerful.oj.common.exception.ParamException;
import com.cheerful.oj.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @AUTHOR: Wang Zhifu
 * @PROJECT_NAME: oj_system
 * @DATE: 2022/3/31 10:35
 * @DESCRIPTION: 全局处理异常
 */
@Slf4j
@ControllerAdvice
public class ServerExceptionResolver {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<String> resolveException(Exception e){
        e.printStackTrace();
        Result<String> response = new Result<>();
        response.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        response.setMsg("error");
        response.setData(e.getMessage());
        return response;
    }

    @ExceptionHandler(ParamException.class)@ResponseBody
    public Result<String> resolveMyException(ParamException ex){
        //打印完整的异常信息
        ex.printStackTrace();
        //创建result
        Result<String> result = new Result<>();
        //设置result属性
        result.setData(ex.getMsg());
        result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        result.setMsg(ex.getMsg());
        //保存自定义异常日志
        log.error(ex.getMsg());
        return result;
    }
}
