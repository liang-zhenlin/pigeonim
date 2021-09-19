package com.liang.pigeon.route;

import com.liang.pigeonim.common.entity.ApiResult;
import com.liang.pigeonim.common.exception.BaseException;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/13 7:14 PM
 */
public class RouteException extends BaseException {

    public RouteException(int code) {
        super(code);
    }

    public RouteException(String message, int code) {
        super(message, code);
    }

    public RouteException(ApiResult error) {
        super(error);
    }

    public RouteException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

    public RouteException(Throwable cause, int code) {
        super(cause, code);
    }

    public RouteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace, code);
    }
}
