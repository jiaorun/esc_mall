package com.esc.mall.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UmsPermission implements Serializable {
    private Long id;

    /**
     * 父级权限id
     *
     * @mbg.generated
     */
    private Long pid;

    /**
     * 名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 权限值
     *
     * @mbg.generated
     */
    private String value;

    /**
     * 图标
     *
     * @mbg.generated
     */
    private String icon;

    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     *
     * @mbg.generated
     */
    private Integer type;

    /**
     * 前端资源路径
     *
     * @mbg.generated
     */
    private String uri;

    /**
     * 启用状态；0->禁用；1->启用
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 排序
     *
     * @mbg.generated
     */
    private Integer sort;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", name=").append(name);
        sb.append(", value=").append(value);
        sb.append(", icon=").append(icon);
        sb.append(", type=").append(type);
        sb.append(", uri=").append(uri);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", sort=").append(sort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}