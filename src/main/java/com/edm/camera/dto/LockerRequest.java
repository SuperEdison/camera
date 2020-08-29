package com.edm.camera.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Edm
 * @create 2020-08-08 11:21
 * @email edisonmiao@icloud.com
 */
@Data
public class LockerRequest {

    @ApiModelProperty("门锁id")
    @JSONField(name = "ID")
    String id;
    @ApiModelProperty("门锁状态")
    @JSONField(name = "door State")
    String doorStatus;
    @ApiModelProperty("锁状态")
    @JSONField(name = "lock state")
    String lockStatus;
    @ApiModelProperty("蓝牙")
    String bluetooth;
    @ApiModelProperty("电量 1-100")
    String electricity;
    @JSONField(name = "ser")
    String serial;
}
