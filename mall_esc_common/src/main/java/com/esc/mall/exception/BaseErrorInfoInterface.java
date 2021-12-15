package com.esc.mall.exception;

/**
 * 基础接口类(自定义的错误描述枚举类需实现该接口)
 *
 * @author jiaorun
 * @date 2021/08/16 14:52
 **/
public interface BaseErrorInfoInterface {

    /**
     * 状态码
     *
     * @return java.lang.Long
     * @author jiaorun
     * @date 2021/08/16 14:54
     */
    Long getCode();

    /**
     * 提示信息
     *
     * @return java.lang.String
     * @author jiaorun
     * @date 2021/08/16 14:54
     */
    String getMessage();
}
