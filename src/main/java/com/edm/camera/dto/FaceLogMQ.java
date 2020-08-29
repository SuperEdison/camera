package com.edm.camera.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Edm
 * @create 2020-08-04 23:42
 * @email edisonmiao@icloud.com
 */
@Data
@ApiModel("人脸日志dto")
public class FaceLogMQ {
    @JSONField(name = "channelId")
    @ApiModelProperty("通道 ID")
    private String channelId;

    @JSONField(name = "channelName")
    @ApiModelProperty("通道名称")
    private String channelName;

    @JSONField(name = "faceImageUrl")
    @ApiModelProperty("人脸抓拍图片,如果涉及到内外网，地址 会以”|”分隔 ")
    private String faceImageUrl;

    @JSONField(name = "pictureUrl")
    @ApiModelProperty("场景图,如果涉及到内外网，地址会以”|” 分隔")
    private String pictureUrl;

    @JSONField(name = "recAge")
    @ApiModelProperty("年龄 ")
    private Integer age;

    @JSONField(name = "recGender")
    @ApiModelProperty("性别   0：未知，1：男， 2：女")
    private Integer sex;

    @JSONField(name = "recFringe")
    @ApiModelProperty("刘海   0 无，1 有 ")
    private Integer fringe;

    @JSONField(name = "recEye")
    @ApiModelProperty("眼镜   0 无，不显示，1 眼镜，2 墨")
    private Integer eye;

    @JSONField(name = "recMouth")
    @ApiModelProperty("嘴型   1 未识别，2 闭嘴，3 张嘴")
    private Integer mouth;

    @JSONField(name = "recMask")
    @ApiModelProperty("口罩 1 未识别， 2 无口罩， 3 戴口罩 ")
    private Integer mask;

    @JSONField(name = "recBeard")
    @ApiModelProperty("胡子 1 未识别， 2 没胡子， 3 有胡子")
    private Integer beard;

    @JSONField(name = "recGlasses")
    @ApiModelProperty("眼镜   0 无，1 眼镜，2 墨镜 ")
    private Integer glasses;

    @JSONField(name = "recEmotion")
    @ApiModelProperty("表情   0 微笑，1 愤怒，2 悲伤，3 厌恶， 4 害怕，5 惊讶，6 正常，7 大笑，8 高兴， 9 困惑，10 尖叫 ")
    private Integer emotion;

    @JSONField(name = "beginTime")
    @ApiModelProperty("抓拍开始时间 ")
    private String beginTime;

    @JSONField(name = "endTime")
    @ApiModelProperty("抓拍结束时间 ")
    private String endTime;

    @JSONField(name = "serviceCode")
    @ApiModelProperty("服务编码")
    private String serviceCode;

    @JSONField(name = "appearTimes")
    @ApiModelProperty("出现次数(可忽略)")
    private String appearTimes;

    @JSONField(name = "hited")
    @ApiModelProperty("是否识别，0 抓拍，1 识别(可忽略)")
    private String recognition;

    @JSONField(name = "similarFaces")
    @ApiModelProperty("陌生人此参数为空")
    private List<SimilarFaceDTO> similarFaces;

    @ApiModelProperty("是否陌生人0-陌生人 1-非陌生人")
    private Integer similar;

}
