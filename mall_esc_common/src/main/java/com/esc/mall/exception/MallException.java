package com.esc.mall.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义异常类
 * @author jiaorun
 * @date 2021/08/27 17:01
 **/
@Data
public class MallException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 8354478828410047920L;

    /**
     * 错误码
     */
    private long errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    public MallException() {}

    public MallException(BaseErrorInfoInterface baseErrorInfoInterface) {
        this.errorCode = baseErrorInfoInterface.getCode();
        this.errorMessage = baseErrorInfoInterface.getMessage();
    }

    public MallException(long errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
