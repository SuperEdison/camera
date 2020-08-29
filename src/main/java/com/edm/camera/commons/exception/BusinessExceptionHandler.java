package com.edm.camera.commons.exception;

import com.edm.camera.commons.enums.ErrorCode;
import com.edm.camera.response.ApiResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: Edm
 * @Date: 8/23/2019 5:21 PM
 */
@RestControllerAdvice
public class BusinessExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ApiResult<ErrorCode> serviceException(BusinessException s) {
        return s.getErrorCode().equals(ErrorCode.FAIL) ? ApiResult.fail(s.getMessage()) : ApiResult.fail(s.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    public ApiResult<ErrorCode> errorException(Exception e) {
        e.printStackTrace();
        return ApiResult.fail(ErrorCode.SERVICE_BUSIED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResult<ErrorCode> requestMissingServletRequest(MissingServletRequestParameterException ex) {
        return ApiResult.fail(ErrorCode.VALID_PARAMETER);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult<ErrorCode> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        return ApiResult.fail(ErrorCode.REQUEST_METHOD_ERROR);
    }
}
