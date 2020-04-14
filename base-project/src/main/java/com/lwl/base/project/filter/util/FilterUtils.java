package com.lwl.base.project.filter.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FilterUtils {

    public static void responseResult(HttpServletResponse response, Object data) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(data));
        writer.flush();
    }
}
