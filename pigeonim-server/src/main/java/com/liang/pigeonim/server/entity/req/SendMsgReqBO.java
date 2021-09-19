package com.liang.pigeonim.server.entity.req;

import com.liang.pigeonim.common.entity.req.BaseRequest;
import lombok.Data;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/4 6:10 PM
 */
@Data
public class SendMsgReqBO extends BaseRequest {

    /**
     * 消息
     */
    private String message;

    /**
     * 用户ID
     */
    private Long userId;
}
