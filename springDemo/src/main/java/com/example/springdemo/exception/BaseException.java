package com.example.springdemo.exception;

import com.example.springdemo.inter.StatusCode;
import com.example.springdemo.inter.em.AppCode;
import lombok.Getter;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-09-12 17:01
 **/
@Getter
public class BaseException extends RuntimeException {
    private int code;
    private String msg;

    // 手动设置异常
    public BaseException(StatusCode statusCode, String message) {
        // message用于用户设置抛出错误详情，例如：当前价格-5，小于0
        super(message);
        // 状态码
        this.code = statusCode.getCode();
        // 状态码配套的msg
        this.msg = statusCode.getMsg();
    }

    // 默认异常使用APP_ERROR状态码
    public BaseException(String message) {
        super(message);
        this.code = AppCode.APP_ERROR.getCode();
        this.msg = AppCode.APP_ERROR.getMsg();
    }
    
}
