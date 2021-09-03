package com.esc.mall.exception;

import com.esc.mall.api.result.MallResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 * @author jiaorun
 * @date 2021/08/27 17:16
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     * @author jiaorun
     * @date 2021/08/27 17:27
     * @param req
     * @param e
     * @return com.esc.mall.api.result.MallResult
     */
    @ExceptionHandler(value = MallException.class)
    @ResponseBody
    public MallResult mallExceptionHandler(HttpServletRequest req, MallException e) {
        log.error("业务异常,异常原因: {}", e.getErrorMessage());
        return MallResult.failed(e.getErrorCode(), e.getErrorMessage());
    }

    /**
     * 处理空指针异常
     * @author jiaorun
     * @date 2021/08/27 17:34
     * @param req
     * @param e
     * @return com.esc.mall.api.result.MallResult
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public MallResult exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("空指针异常,异常原因: {}", e);
        return MallResult.failed(ResultInfoEnum.NULL_POINT_FOUND);
    }

    /**
     * 处理其他异常
     * @author jiaorun
     * @date 2021/08/27 17:36
     * @param req
     * @param e
     * @return com.esc.mall.api.result.MallResult
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public MallResult exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常,异常原因: {}", e);
        return MallResult.failed(ResultInfoEnum.FAILED);
    }
}
