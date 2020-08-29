package com.edm.camera.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Edm
 * @create 2020-08-05 20:59
 * @email edisonmiao@icloud.com
 */
@Data
@ApiModel("不是陌生人")
public class SimilarFaceDTO {
    @JSONField(name = "faceImageUrl")
    @ApiModelProperty("人脸库中图")
    private String faceImageUrl;

    @JSONField(name = "name")
    @ApiModelProperty("名称 ")
    private String name;

    @JSONField(name = "personTypeId")
    @ApiModelProperty("人员类型 1 学生 2 教师 3 保安 4 宿管 5 其他 ")
    private String personTypeId;

    @JSONField(name = "personTypeName")
    @ApiModelProperty("人员类型名称")
    private String personTypeName;

    @JSONField(name = "personId")
    @ApiModelProperty("人员学工号")
    private String personId;

    @JSONField(name = "gender")
    @ApiModelProperty("性别 0：未知，1：男， 2：女")
    private String sex;

    @JSONField(name = "memo")
    @ApiModelProperty("备注")
    private String remark;

    @JSONField(name = "repositoryId")
    @ApiModelProperty("人脸库 id")
    private String repositoryId;

    @JSONField(name = "repositoryName")
    @ApiModelProperty("人脸库名称")
    private String repositoryName;

    @JSONField(name = "repositoryColor")
    @ApiModelProperty("人脸库颜色 1-灰色 2-橙色")
    private String repositoryColor;

    @JSONField(name = "similarity")
    @ApiModelProperty("相似度")
    private String similarity;

    @JSONField(name = "contactNum")
    @ApiModelProperty("联系电话")
    private String phone;

    @JSONField(name = "surveillance")
    @ApiModelProperty("是否布控(可忽略)")
    private String surveillance;

    @JSONField(name = "idCode")
    @ApiModelProperty("身份证")
    private String idCard;

    @JSONField(name = "uid")
    @ApiModelProperty("该人员绑定库产生的 id")
    private String uid;

    @JSONField(name = "birthday")
    @ApiModelProperty("生日(可忽略)")
    private String birthday;

    @JSONField(name = "nationality")
    @ApiModelProperty("国籍(可忽略)")
    private String nationality;


}
