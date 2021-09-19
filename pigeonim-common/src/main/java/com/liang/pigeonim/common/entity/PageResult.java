package com.liang.pigeonim.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/6/15 5:19 PM
 */
@Data
public class PageResult implements Serializable {

    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 数据模型
     */
    private List<?> content;
}
