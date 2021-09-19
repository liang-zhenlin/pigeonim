package com.liang.pigeonim.common.entity.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/4 6:07 PM
 */
@Data
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = 6224427346635032283L;

    /**
     * 唯一ID
     */
    private String uniId;

    /**
     * 当前请求的时间戳
     */
    private int timeStamp;

    public BaseRequest() {
        this.setTimeStamp((int) System.currentTimeMillis() / 1000);
    }
}
