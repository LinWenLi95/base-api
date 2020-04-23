package com.lwl.base.api.common.pojo;

import org.springframework.util.StringUtils;

/**
 * 排序枚举
 * @author linwenli
 * @date 2020/04/23
 */
public enum SortEnum{
    /**升序*/
    ASC("asc",1),
    /**降序*/
    DESC("desc",0),
    /**空或错误*/
    NONE(null,-1);

    private final String value;
    private final Integer code;

    SortEnum(String value,Integer code) {
        this.value = value;
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public Integer getCode() {
        return code;
    }

    public static SortEnum getSortEnum(String value) {
        if (StringUtils.isEmpty(value)) {
            return NONE;
        }
        for (SortEnum sortEnum : values()) {
            if (sortEnum.getValue().equals(value.toLowerCase())) {
                return sortEnum;
            }
        }
        return NONE;
    }
}
