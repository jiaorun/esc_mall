package com.esc.mall.exception;

import com.esc.mall.api.result.MallResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理类
 *
 * @author jiaorun
 * @date 2021/08/27 17:16
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义异常
     *
     * @param e
     * @return com.esc.mall.api.result.MallResult
     * @author jiaorun
     * @date 2021/08/27 17:27
     */
    @ExceptionHandler(value = MallException.class)
    @ResponseBody
    public MallResult mallExceptionHandler(MallException e) {
        LOGGER.error("业务异常,异常原因: {}", e.getErrorMessage());
        return MallResult.failed(e.getErrorCode(), e.getErrorMessage());
    }

    /**
     * 处理空指针异常
     *
     * @param e
     * @return com.esc.mall.api.result.MallResult
     * @author jiaorun
     * @date 2021/08/27 17:34
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public MallResult exceptionHandler(NullPointerException e) {
        LOGGER.error("空指针异常,异常原因: {}", e);
        return MallResult.failed(ResultInfoEnum.NULL_POINT_FOUND);
    }

    /**
     * 参数校验异常
     *
     * @param e
     * @return com.esc.mall.api.result.MallResult
     * @author jiaorun
     * @date 2021/09/15 15:26
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public MallResult exceptionHandler(MethodArgumentNotValidException e) {
        LOGGER.error("参数校验异常,异常原因: {}", e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getDefaultMessage())
                .collect(Collectors.toList());
        return MallResult.failed(ResultInfoEnum.VALIDATE_FAILED, collect);
    }

    /**
     * SQL异常
     *
     * @param e
     * @return com.esc.mall.api.result.MallResult
     * @author jiaorun
     * @date 2021/09/15 15:34
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public MallResult exceptionHandler(SQLException e) {
        LOGGER.error("SQL异常，异常原因: {}", e);
        return MallResult.failed("SQL执行异常", null);
    }

    /**
     * 处理其他异常
     *
     * @param e
     * @return com.esc.mall.api.result.MallResult
     * @author jiaorun
     * @date 2021/08/27 17:36
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public MallResult exceptionHandler(Exception e) {
        LOGGER.error("未知异常,异常原因: {}", e);
        return MallResult.failed(ResultInfoEnum.FAILED);
    }
}
