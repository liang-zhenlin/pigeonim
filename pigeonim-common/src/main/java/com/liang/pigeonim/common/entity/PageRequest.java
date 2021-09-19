package com.liang.pigeonim.common.entity;

import lombok.Data;

import javax.validation.constraints.Max;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/6/15 5:18 PM
 */
@Data
public abstract class PageRequest {

    private int pageNum = 1;

    @Max(value = 100)
    private int pageSize = 10;
}
