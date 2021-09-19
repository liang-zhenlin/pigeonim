package com.liang.pigeon.route.entity.req;

import com.liang.pigeonim.common.entity.req.BaseRequest;
import lombok.Data;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/10 3:16 PM
 */
@Data
public class LoginReqBO extends BaseRequest {

    private Long userId;

    private String userName;
}
