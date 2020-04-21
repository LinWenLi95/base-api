package com.lwl.base.project.util;

import org.springframework.util.StringUtils;

/**
 * @author linwenli
 * @date 2020/04/21
 */
public class UrlUtils {

    /**
     * 校验是否含路径变量{xx}
     * @param url url
     * @return boolean
     */
    public static boolean isPathVariable(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        }
        //计算包含的‘{’的个数
        int count = url.length() - url.replace("{", "").length();
        return count > 0;
    }

    /**
     * 将url中的{xx}路径变量改为‘*’，用于匹配请求url
     * （可使用spring的AntPathMatcher类进行url匹配，可使用占位符来匹配：?(单个字符)|*(多个字符,不可跨url中的/)|**(匹配多个字符任意层/)来匹配）
     * @param url url
     * @return String
     */
    public static String toMatchesString(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        //计算包含的‘{’的个数
        int count = url.length() - url.replace("{", "").length();
        for (int i = 0; i < count; i++) {
            int leftIndex = url.indexOf("{");
            int rightIndex = url.indexOf("}");
            url = url.replace(url.substring(leftIndex, rightIndex + 1), "*");
        }
        return url;
    }
}
