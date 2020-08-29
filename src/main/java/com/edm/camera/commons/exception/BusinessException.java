package com.edm.camera.commons.exception;

import com.edm.camera.commons.enums.ErrorCode;
import lombok.Data;

/**
 * @Author: Edm
 * @Date: 8/23/2019 3:29 PM
 */
@Data
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
        this.errorCode = ErrorCode.FAIL;
    }
}
