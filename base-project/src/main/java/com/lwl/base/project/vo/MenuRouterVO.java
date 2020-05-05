package com.lwl.base.project.vo;

import lombok.Data;

import java.util.List;

/**
 * @author LinWenLi
 * @since 2020-05-05
 */
@Data
public class MenuRouterVO {

    private Integer id;
    private Integer pid;
    private String path;
    private String component;
    private String name;
    private MetaVO meta;
    private List<MenuRouterVO> children;

    public void setMeta(String title, String icon) {
        if (meta == null) {
            meta = new MetaVO();
        }
        meta.setTitle(title);
        meta.setIcon(icon);
    }

    @Data
    static class MetaVO {
        private String title;
        private String icon;
    }
}


