package com.lwl.test;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwl.base.api.common.pojo.PageCondition;
import com.lwl.base.api.common.vo.Result;
import com.lwl.base.project.BaseProjectApplication;
import com.lwl.base.project.dto.GetUserPageDTO;
import com.lwl.base.project.service.ISysUserService;
import com.lwl.base.project.vo.GetUserPageVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LinWenLi
 * @since 2020-04-26
 */
@SpringBootTest(classes = BaseProjectApplication.class)
public class TestController {

    @Autowired
    private ISysUserService service;

    @Test
    public void test1() {
        GetUserPageDTO getUserPageDTO = new GetUserPageDTO();
        PageCondition pageCondition = new PageCondition();
        Result<Page<GetUserPageVO>> userPage = service.getUserPage(getUserPageDTO, pageCondition);
        pageCondition.setOrderBy("id");
        pageCondition.setSort("desc");
        Result<Page<GetUserPageVO>> userPage1 = service.getUserPage(getUserPageDTO, pageCondition);
        getUserPageDTO.setUsername("user");
        Result<Page<GetUserPageVO>> userPage21 = service.getUserPage(getUserPageDTO, pageCondition);
        System.out.println();
    }
}
