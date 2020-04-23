package com.lwl.base.project.entity;

import java.io.Serializable;
import lombok.Data;
import java.sql.Timestamp;

/**
* 系统 权限表
* @author LinWenLi
* @date 2020/04/18
*/
@Data
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

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
    /**是否已删除*/
    private Boolean isDel;
}
