package com.edm.camera.response;

import com.edm.camera.commons.enums.ErrorCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Edm
 * @create 2020-08-05 0:17
 * @email edisonmiao@icloud.com
 */
@Data
public class ApiResult<T> {
    @ApiModelProperty(notes = "返回码，0表示成功，非0表示失败")
    private int code;
    @ApiModelProperty(notes = "返回消息，成功为,失败为具体失败信息")
    private String message;
    /**
     * 返回的数据
     */
    @ApiModelProperty(notes = "返回的具体数据")
    private T resultData;


    public static  <T> ApiResult<T> ok() {
        return ok(null);
    }

    public static <T> ApiResult<T> ok(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.code = 0;
        result.message = "成功";
        result.resultData = data;
        return result;
    }

    public static <T> ApiResult<T> fail(ErrorCode errorCode) {
        ApiResult<T> result = new ApiResult<>();
        result.code = errorCode.getCode();
        result.message = errorCode.getMessage();
        result.resultData = null;
        return result;
    }

    public static <T> ApiResult<T> fail(String message) {
        ApiResult<T> result = new ApiResult<>();
        result.code = ErrorCode.FAIL.getCode();
        result.message = message;
        result.resultData = null;
        return result;
    }
}
