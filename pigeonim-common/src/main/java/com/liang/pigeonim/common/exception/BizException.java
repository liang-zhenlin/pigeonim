package com.liang.pigeonim.common.exception;

import com.liang.pigeonim.common.entity.ApiResult;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/6/16 9:25 AM
 */
public class BizException extends BaseException {

    public BizException(int code) {
        super(code);
    }

    public BizException(String message, int code) {
        super(message, code);
    }

    public BizException(ApiResult error) {
        super(error);
    }

    public BizException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

    public BizException(Throwable cause, int code) {
        super(cause, code);
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace, code);
    }

}
