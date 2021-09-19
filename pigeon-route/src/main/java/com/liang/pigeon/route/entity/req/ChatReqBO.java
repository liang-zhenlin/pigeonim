package com.liang.pigeon.route.entity.req;

import com.liang.pigeonim.common.entity.req.BaseRequest;
import lombok.Data;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/12 5:33 PM
 */
@Data
public class ChatReqBO extends BaseRequest {

    private Long userId;

    private String message;
}
