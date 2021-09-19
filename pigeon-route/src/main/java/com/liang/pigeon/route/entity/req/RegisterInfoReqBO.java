package com.liang.pigeon.route.entity.req;

import com.liang.pigeonim.common.entity.req.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/10 3:19 PM
 */
@Data
@Builder
public class RegisterInfoReqBO extends BaseRequest {

    private Long userId;

    private String userName;
}
