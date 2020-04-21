package com.spark.platform.adminapi.vo;


import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.vo
 * @ClassName: MenuVue
 * @Author: wangdingfeng
 * @Description: vue菜单
 * @Date: 2020/3/16 15:47
 * @Version: 1.0
 */
public class MenuVue{

    private Long id;
    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父级菜单主键
     */
    private Long pid;
    /**
     * 是否外链
     */
    private boolean iFrame;

    /**
     * 路径
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 权限
     */
    private String permission;
    /**
     * 是否隐藏
     */
    private boolean hidden;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;

    private Meta meta;

    private List<MenuVue> children;

    public static class Meta {
        /**
         * 设置该路由在侧边栏和面包屑中展示的名字
         */
        private String title;
        /**
         * 设置该路由的图标
         */
        private String icon;
        /**
         * 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
         */
        private Boolean noCache;

        public Meta(){

        }
        public Meta(String title,String icon,Boolean noCache){
            this.title = title;
            this.icon = icon;
            this.noCache = noCache;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Boolean getNoCache() {
            return noCache;
        }

        public void setNoCache(Boolean noCache) {
            this.noCache = noCache;
        }
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<MenuVue> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVue> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public boolean isiFrame() {
        return iFrame;
    }

    public void setiFrame(boolean iFrame) {
        this.iFrame = iFrame;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
