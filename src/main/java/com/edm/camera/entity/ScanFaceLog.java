package com.edm.camera.entity;

import com.edm.camera.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 扫描人脸日志表
 * </p>
 *
 * @author Edm
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="ScanFaceLog对象", description="扫描人脸日志表")
public class ScanFaceLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "刘海   0 无，1 有 ")
    private Integer fringe;

    @ApiModelProperty(value = "眼镜   0 无，不显示，1 眼镜，2 墨")
    private Integer eye;

    @ApiModelProperty(value = "嘴型   1 未识别，2 闭嘴，3 张嘴")
    private Integer mouth;

    @ApiModelProperty(value = "口罩   1 未识别， 2 无口罩， 3 戴口罩 ")
    private Integer mask;

    @ApiModelProperty(value = "胡子   1 未识别， 2 没胡子， 3 有胡子")
    private Integer beard;

    @ApiModelProperty(value = "眼镜   0 无，1 眼镜，2 墨镜")
    private Integer glasses;

    @ApiModelProperty(value = "表情   0 微笑，1 愤怒，2 悲伤，3 厌恶， 4 害怕，5 惊讶，6 正常，7 大笑，8 高兴， 9 困惑，10 尖叫")
    private Integer emotion;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "服务编码")
    private String serviceCode;

    @ApiModelProperty(value = "出现时间")
    private Integer appearTimes;

    @ApiModelProperty(value = "是否识别，0 抓拍，1 识别(可忽略)")
    private Integer recognition;

    @ApiModelProperty(value = "是否陌生人0-陌生人 1-非陌生人")
    private Integer similar;


}
