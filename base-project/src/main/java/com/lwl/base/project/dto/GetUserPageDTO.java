package com.lwl.base.project.dto;

import com.lwl.base.project.entity.SysUser;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 获取用户分页列表接口入参实体
 * @author LinWenLi
 * @since 2020-04-24
 */
@Data
public class GetUserPageDTO {

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

    public <T> T ge(Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(this, t);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
