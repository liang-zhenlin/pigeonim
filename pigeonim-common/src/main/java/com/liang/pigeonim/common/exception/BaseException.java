package com.liang.pigeonim.common.exception;

import com.liang.pigeonim.common.entity.ApiResult;
import lombok.Data;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/6/15 5:17 PM
 */
@Data
public abstract class BaseException extends RuntimeException {

    private int code;

    private String message;

    public BaseException(int code) {
        this.code = code;
    }

    public BaseException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(ApiResult error) {
        this(error.getMsg(), error.getCode());
    }

    public BaseException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public BaseException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message;
    }
}
