package com.esc.mall.api.result;

import com.esc.mall.exception.BaseErrorInfoInterface;
import com.esc.mall.exception.ResultInfoEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回对象
 * @author jiaorun
 * @date 2021/08/16 14:39
 **/
@Data
public class MallResult<T> implements Serializable {

    private static final long serialVersionUID = 7852641816468972854L;

    /**
     * 状态码
     */
    private long code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 数据封装
     */
    private T data;

    public MallResult() {}

    public MallResult(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public MallResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public MallResult(ResultInfoEnum resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public MallResult(ResultInfoEnum resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 成功未返回结果
     * @param <T>
     * @return
     */
    public static <T> MallResult<T> success() {
        return new MallResult<T>(ResultInfoEnum.SUCCESS);
    }

    /**
     * 成功返回结果
     * @param <T>
     * @return
     */
    public static  <T> MallResult<T> success(T data) {
        return new MallResult<T>(ResultInfoEnum.SUCCESS, data);
    }

    /**
     * 成功返回结果
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> MallResult<T> success(String message, T data) {
        return new MallResult<T>(ResultInfoEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败未返回结果
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> MallResult<T> failed(long code, String message) {
        return new MallResult<T>(code, message);
    }

    /**
     * 失败未返回结果
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> MallResult<T> failed(BaseErrorInfoInterface errorCode) {
        return new MallResult<T>(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * 失败返回结果
     * @param errorCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T> MallResult<T> failed(BaseErrorInfoInterface errorCode, T data) {
        return new MallResult<T>(errorCode.getCode(), errorCode.getMessage(), data);
    }

    /**
     * 失败返回结果
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> MallResult<T> failed(String message, T data) {
        return new MallResult<T>(ResultInfoEnum.FAILED.getCode(), message, data);
    }

    /**
     * 未登录
     * @param data
     * @param <T>
     * @return
     */
    public static <T> MallResult<T> unauthorized(T data) {
        return new MallResult<T>(ResultInfoEnum.UNAUTHORIZED.getCode(), ResultInfoEnum.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权
     * @param data
     * @param <T>
     * @return
     */
    public static <T> MallResult<T> forbidden(T data) {
        return new MallResult<T>(ResultInfoEnum.FORBIDDEN.getCode(), ResultInfoEnum.FORBIDDEN.getMessage(), data);
    }
}
