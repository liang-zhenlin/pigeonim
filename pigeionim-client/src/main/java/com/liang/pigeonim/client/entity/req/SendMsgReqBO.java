package com.liang.pigeonim.client.entity.req;

import com.liang.pigeonim.common.entity.req.BaseRequest;
import lombok.Data;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/7 11:02 AM
 */
@Data
public class SendMsgReqBO extends BaseRequest {

    private Long userId;

    private String message;
}
