package com.esc.mall.exception;

/**
 * 断言处理类 用于抛出自定义异常
 *
 * @author jiaorun
 * @date 2021/09/15 11:29
 **/
public class Asserts {

    public static void fail(String message) {
        throw new MallException(message);
    }

}
