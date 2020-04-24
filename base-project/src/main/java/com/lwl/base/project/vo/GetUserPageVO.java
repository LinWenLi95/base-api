package com.lwl.base.project.vo;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * 获取用户分页列表接口出参实体
 * @author LinWenLi
 * @since 2020-04-24
 */
@Data
public class GetUserPageVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 昵称（显示用户名）
     */
    private String nickName;

    /**
     * 用户名（登录用户名）
     */
    private String username;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

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
}
