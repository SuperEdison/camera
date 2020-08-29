package com.edm.camera.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Edm
 * @create 2020-08-27 20:32
 * @email edisonmiao@icloud.com
 */
@Data
public class FaceLogDTO {
    @ApiModelProperty(value = "通道ID")
    private String channelId;

    @ApiModelProperty(value = "通道名称")
    private String channelName;

    @ApiModelProperty(value = "人脸抓拍图片,如果涉及到内外网，地址 会以”|”分隔 ")
    private String faceImageUrl;

    @ApiModelProperty(value = "场景图,如果涉及到内外网，地址会以”|” 分隔")
    private String pictureUrl;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别   0：未知，1：男， 2：女")
    private Integer sex;

    @ApiModelProperty(value = "开始时间")
    private String beginTime;

    @ApiModelProperty(value = "服务编码")
    private String serviceCode;

    @ApiModelProperty(value = "出现时间")
    private Integer appearTimes;

    @ApiModelProperty(value = "是否识别，0 抓拍，1 识别(可忽略)")
    private Integer recognition;

    @ApiModelProperty(value = "是否陌生人0-陌生人 1-非陌生人")
    private Integer similar;

    @ApiModelProperty(value = "人脸库中图")
    private String faceImageLibUrl;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "相似度")
    private Integer similarity;

    @ApiModelProperty(value = "联系电话")
    private String phone;

}
