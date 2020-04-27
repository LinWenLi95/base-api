package com.lwl.base.project.config.mybatisplus;

/**
 * @author LinWenLi
 * @since 2020-04-27
 */


/**
 * 自定义全局删除方法
 */

public enum MySqlMethod {


    /**
     * 分页列表<br/>
     * 根据 entity 条件查询记录（可传入非entity对象作为查询条件并响应非entity的Page内容）
     */
    QUERY_PAGE("queryPage", "根据 entity 条件查询记录（可传入非 entity 对象作为查询条件）", "<script>%s SELECT %s FROM %s %s %s\n</script>");


    private final String method;
    private final String desc;
    private final String sql;

    MySqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

    public String getSql() {
        return sql;
    }

}