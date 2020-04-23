package com.lwl.base.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 系统 权限表
 * @author LinWenLi
 * @since 2020-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * url
     */
    private String url;

    /**
     * 请求类型
     GET,POST,PUT,DELETE,OPTIONS,HEAD,TRACE,CONNECT
     */
    private String method;

    /**
     * 状态 0禁用,1启动
     */
    private Boolean state;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建者id
     */
    private Integer creatorId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者id
     */
    private Integer updaterId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否已删除
     */
    private Boolean isDel;

}
