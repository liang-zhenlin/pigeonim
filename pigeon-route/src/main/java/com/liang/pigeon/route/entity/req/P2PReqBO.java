package com.liang.pigeon.route.entity.req;

import com.liang.pigeonim.common.entity.req.BaseRequest;
import lombok.Data;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/12 5:30 PM
 */
@Data
public class P2PReqBO extends BaseRequest {

    private Long userId;

    private Long receiveUserId;

    private String message;
}
