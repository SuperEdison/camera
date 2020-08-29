package com.edm.camera.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Edm
 * @create 2020-08-05 21:08
 * @email edisonmiao@icloud.com
 */
@Data
@ApiModel("人脸日志流水")
public class FaceLogDataMQ {
    @JSONField(name = "method")
    @ApiModelProperty("方法描述")
    private String method;

    @JSONField(name = "info")
    @ApiModelProperty("详情")
    private FaceLogMQ info;

}
