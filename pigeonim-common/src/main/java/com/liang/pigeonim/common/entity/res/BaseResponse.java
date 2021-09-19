package com.liang.pigeonim.common.entity.res;

import com.liang.pigeonim.common.entity.ApiResult;
import com.liang.pigeonim.common.enumeration.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/6 8:39 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse<T> implements Serializable {

    private int code;

    private String message;

    private String reqNum;

    private T data;

    public static <T> BaseResponse<T> success() {
        return success(null, null);
    }

    public static BaseResponse success(Object data) {
        return success(data, null);
    }

    public static BaseResponse success(String msg) {
        return success(null, msg);
    }

    public static BaseResponse success(Object data, String message) {
        return BaseResponse.builder().code(ResultEnum.SUCCESS.getCode()).message(message).data(data).build();
    }

    public static BaseResponse fail(String msg) {
        return BaseResponse.builder().code(ResultEnum.COMMON_ERROR.getCode()).message(msg).build();
    }

    public static BaseResponse fail(ApiResult error) {
        return BaseResponse.builder().code(error.getCode()).message(error.getMsg()).build();
    }

    public static BaseResponse fail(ApiResult error, Object data) {
        return BaseResponse.builder().code(error.getCode()).message(error.getMsg()).data(data).build();
    }

    public static BaseResponse fail(ApiResult error,String msg, Object data) {
        return BaseResponse.builder().code(error.getCode()).message(error.getMsg()+":"+msg).data(data).build();
    }

    public static BaseResponse fail(int code, String msg) {
        return BaseResponse.builder().code(code).message(msg).build();
    }

    public static BaseResponse fail(int code, String msg, Object data) {
        return BaseResponse.builder().code(code).message(msg).data(data).build();
    }
}
