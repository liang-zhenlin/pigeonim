package com.liang.pigeon.route.entity.res;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/10 3:34 PM
 */
@Data
@Builder
public class RegisterInfoResVO implements Serializable {

    private static final long serialVersionUID = -1070845188386672076L;

    private Long userId;

    private String userName;
}
