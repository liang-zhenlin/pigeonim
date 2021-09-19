package com.liang.pigeon.route.entity.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/10 3:36 PM
 */
@Data
public class PigeonIMServerResVO implements Serializable {

    private static final long serialVersionUID = -4188725156384254542L;

    private String ip;

    private Integer httpPort;
}
