package com.edm.camera.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Edm
 * @create 2020-08-05 0:20
 * @email edisonmiao@icloud.com
 */
@Data
public class LockerRegisterRequest {
    @NotBlank(message = "唯一id不能为空")
    private String uniCode;
    private Integer code;
    @NotNull(message = "锁状态不能为空1-锁住 0为开锁")
    private Integer status;

}
