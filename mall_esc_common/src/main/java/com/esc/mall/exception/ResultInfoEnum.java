package com.esc.mall.exception;

/**
 * 通用API返回对象 枚举类
 * @author jiaorun
 * @date 2021/08/16 14:41
 **/
public enum ResultInfoEnum implements BaseErrorInfoInterface {

    SUCCESS(200, "操作成功！"),
    VALIDATE_FAILED(400, "参数校验异常！"),
    UNAUTHORIZED(401, "暂未登录或token已过期！"),
    FORBIDDEN(403, "没有相关权限！"),
    NOT_FOUND(404, "未找到该资源！"),
    NULL_POINT_FOUND(407, "空指针异常！"),
    USERNAME_PASSWORD_ERROR(408, "用户名或密码错误！"),
    BUSINESS_ERROR(410, "业务异常！"),
    FAILED(500, "服务器内部错误！"),
    SERVER_BUSY(503,"服务器正忙，请稍后再试！");

    /**
     * 状态码
     */
    private long code;

    /**
     * 提示信息
     */
    private String message;

    private ResultInfoEnum(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
