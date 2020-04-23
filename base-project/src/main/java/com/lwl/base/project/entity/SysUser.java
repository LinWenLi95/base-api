package com.lwl.base.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统 用户表
 * @author LinWenLi
 * @since 2020-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 密码，加密存储
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

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

    /**
     * 是否已删除
     */
    private Boolean isDel;


}
