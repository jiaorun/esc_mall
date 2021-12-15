package com.esc.mall.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UmsMember implements Serializable {
    private Long id;

    private Long memberLevelId;

    /**
     * 用户名
     *
     * @mbg.generated
     */
    private String username;

    /**
     * 密码
     *
     * @mbg.generated
     */
    private String password;

    /**
     * 昵称
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     * 手机号码
     *
     * @mbg.generated
     */
    private String phone;

    /**
     * 邮箱
     *
     * @mbg.generated
     */
    private String email;

    /**
     * 身份证
     *
     * @mbg.generated
     */
    private String idCard;

    /**
     * 帐号启用状态:0->禁用；1->启用
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * 注册时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 头像
     *
     * @mbg.generated
     */
    private String icon;

    /**
     * 性别：0->未知；1->男；2->女
     *
     * @mbg.generated
     */
    private Integer gender;

    /**
     * 生日
     *
     * @mbg.generated
     */
    private Date birthday;

    /**
     * 所做城市
     *
     * @mbg.generated
     */
    private String city;

    /**
     * 职业
     *
     * @mbg.generated
     */
    private String job;

    /**
     * 个性签名
     *
     * @mbg.generated
     */
    private String personalizedSignature;

    /**
     * 用户来源
     *
     * @mbg.generated
     */
    private Integer sourceType;

    /**
     * 积分
     *
     * @mbg.generated
     */
    private Integer integration;

    /**
     * 成长值
     *
     * @mbg.generated
     */
    private Integer growth;

    /**
     * 剩余抽奖次数
     *
     * @mbg.generated
     */
    private Integer luckeyCount;

    /**
     * 历史积分数量
     *
     * @mbg.generated
     */
    private Integer historyIntegration;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberLevelId=").append(memberLevelId);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", idCard=").append(idCard);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", icon=").append(icon);
        sb.append(", gender=").append(gender);
        sb.append(", birthday=").append(birthday);
        sb.append(", city=").append(city);
        sb.append(", job=").append(job);
        sb.append(", personalizedSignature=").append(personalizedSignature);
        sb.append(", sourceType=").append(sourceType);
        sb.append(", integration=").append(integration);
        sb.append(", growth=").append(growth);
        sb.append(", luckeyCount=").append(luckeyCount);
        sb.append(", historyIntegration=").append(historyIntegration);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}