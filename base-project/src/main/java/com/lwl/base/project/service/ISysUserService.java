package com.lwl.base.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.base.api.common.pojo.PageCondition;
import com.lwl.base.api.common.vo.Result;
import com.lwl.base.project.dto.GetUserPageDTO;
import com.lwl.base.project.entity.SysUser;
import com.lwl.base.project.vo.GetUserPageVO;

import java.util.Optional;

/**
 * 系统 用户表 服务类
 * @author LinWenLi
 * @since 2020-04-23
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 通过username获取用户密码
     * @param username 用户名
     * @return SysUser
     */
    SysUser queryByUsername(String username);

    /**
     * 删除用户
     * @param userId 要删除的用户id
     * @return Result<Object>
     */
    Result<Object> deleteById(String userId);

    /**
     * 获取用户分页列表
     * @param dto       查询条件
     * @param condition 分页/排序条件
     */
    Result<Page<GetUserPageVO>> getPage(GetUserPageDTO dto, PageCondition condition);
}
