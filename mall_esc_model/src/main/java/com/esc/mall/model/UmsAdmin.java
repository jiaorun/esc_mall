package com.esc.mall.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UmsAdmin implements Serializable {
    private Long id;

    private String username;

    private String password;

    /**
     * 头像
     *
     * @mbg.generated
     */
    private String icon;

    /**
     * 邮箱
     *
     * @mbg.generated
     */
    private String email;

    /**
     * 昵称
     *
     * @mbg.generated
     */
    private String nickName;

    /**
     * 备注信息
     *
     * @mbg.generated
     */
    private String note;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 最后登录时间
     *
     * @mbg.generated
     */
    private Date loginTime;

    /**
     * 帐号启用状态：0->禁用；1->启用
     *
     * @mbg.generated
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", icon=").append(icon);
        sb.append(", email=").append(email);
        sb.append(", nickName=").append(nickName);
        sb.append(", note=").append(note);
        sb.append(", createTime=").append(createTime);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}