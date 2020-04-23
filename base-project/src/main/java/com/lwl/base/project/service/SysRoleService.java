package com.lwl.base.project.service;

import com.lwl.base.api.common.base.BaseService;
import com.lwl.base.project.entity.SysRole;

import java.util.List;

/**
* 系统 角色表Service接口
* @author LinWenLi
* @date 2020/04/12
*/
public interface SysRoleService extends BaseService<SysRole>{
    /**
     * 查询用户匹配的角色列表
     * @param userId 用户id
     * @return List<SysRole>
     */
    List<SysRole> queryListByUserId(Integer userId);

    /**
     * 获取有访问权限的角色（后续应改为从缓存中获取）
     * @param url 请求url
     * @return List<SysRole>
     */
    List<SysRole> queryListByUrl(String url);
}