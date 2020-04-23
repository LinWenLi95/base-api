package com.lwl.base.api.common.pojo;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 分页/排序查询需要传入的参数
 * @author linwenli
 * @date 2020/04/23
 */
@Data
public class PageCondition {
    /**当前页码（可选，必须与limit配合使用）*/
    private Integer current;
    /**取出数量（可选，可单独使用）*/
    private Integer limit;
    /**排序字段（可选，必须与sort配合使用）*/
    private String orderBy;
    /**排序方式（可选，值选填：asc|desc，必须与order_by配合使用）*/
    private String sort;

    public SortEnum getSort() {
        return SortEnum.getSortEnum(sort);
    }
}