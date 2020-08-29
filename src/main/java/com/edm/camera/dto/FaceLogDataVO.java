package com.edm.camera.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Edm
 * @create 2020-08-10 20:50
 * @email edisonmiao@icloud.com
 */
@Data
public class FaceLogDataVO {
    @ApiModelProperty("场景图片")
    private String scenePic;

    @ApiModelProperty("人脸图片")
    private String facePic;

    @ApiModelProperty("人脸库图片")
    private String faceLibraryPic;

    @ApiModelProperty("是否陌生人 0-否 1-是")
    private Integer isBlack;

    @ApiModelProperty("锁状态")
    private String lockerStatus;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("相似度")
    private String similarity;
}
