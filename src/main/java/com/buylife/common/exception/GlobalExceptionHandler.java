package com.buylife.common.exception;

import com.buylife.common.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.Optional;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }
    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String requestURI = getRequestURI(request);
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return ResponseResult.fail(e.getMessage());
    }
    /**
     * 自定义异常
     */
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult handleServiceException(CustomException e) {
//        log.error(e.getMessage(), e);
        Integer code = e.getCode();
        return Objects.nonNull(code) ? ResponseResult.fail(code, e.getMessage()) : ResponseResult.fail(e.getMessage());
    }
    /**
     * 通用异常
     */
    @ExceptionHandler({RuntimeException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handleGeneralException(Exception e, HttpServletRequest request) {
        String requestURI = getRequestURI(request);
        log.error("请求地址'{}',发生异常: {}", requestURI, e.getClass().getSimpleName());
        return ResponseResult.fail(e.getMessage());
    }
    /**
     * 参数绑定异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResponseResult.fail(message);
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Optional<FieldError> fieldError = e.getBindingResult().getFieldErrors().stream().findFirst();
        String message = fieldError.map(FieldError::getDefaultMessage).orElse(e.getMessage());
        return ResponseResult.fail(message);
    }
}
