package com.lwl.base.api.common.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * 自定义断言类（后续配合枚举使用）https://mp.weixin.qq.com/s/zdwHINfGng5ffv8L46iETw
 * @author LinWenLi
 */
public class Assert {

    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(@Nullable String object, String message) {
        if (StringUtils.isEmpty(object)) {
            throw new IllegalArgumentException(message);
        }
    }
}
