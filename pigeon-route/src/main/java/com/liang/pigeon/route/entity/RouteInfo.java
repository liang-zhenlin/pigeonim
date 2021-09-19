package com.liang.pigeon.route.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/13 4:41 PM
 */
@Data
@AllArgsConstructor
@Builder
public class RouteInfo {

    private String ip;

    private Integer httpPort;
}
