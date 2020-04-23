package com.lwl.base.project.dto;

import com.lwl.base.api.common.pojo.PageCondition;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author linwenli
 * @date 2020/04/23
 */
@Data
public class GetPermissionPageDTO extends PageCondition {

    /**id*/
    private Integer id;
    /**标题*/
    private String title;
    /**url*/
    private String url;
    /**请求类型 GET,POST,PUT,DELETE,OPTIONS,HEAD,TRACE,CONNECT*/
    private String method;
    /**状态 0禁用,1启动*/
    private Integer state;
    /**描述*/
    private String description;
    /**创建者id*/
    private Integer creatorId;
    /**创建时间*/
    private Timestamp createTime;
    /**更新者id*/
    private Integer updaterId;
    /**更新时间*/
    private Timestamp updateTime;
}
