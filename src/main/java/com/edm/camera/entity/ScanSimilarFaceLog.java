package com.edm.camera.entity;

import java.time.LocalDateTime;
import com.edm.camera.commons.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 扫描非陌生人脸日志
 * </p>
 *
 * @author Edm
 * @since 2020-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="ScanSimilarFaceLog对象", description="扫描非陌生人脸日志")
public class ScanSimilarFaceLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "扫脸日志id")
    private Long scanFaceLogId;

    @ApiModelProperty(value = "人脸库中图")
    private String faceImageUrl;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "人员类型 1 学生 2 教师 3 保安 4 宿管 5 其他 ")
    private Integer personTypeId;

    @ApiModelProperty(value = "人员类型名称")
    private String personTypeName;

    @ApiModelProperty(value = "人员学工号")
    private Long personId;

    @ApiModelProperty(value = "性别 0：未知，1：男， 2：女")
    private Integer sex;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "人脸库 id")
    private Long repositoryId;

    @ApiModelProperty(value = "人脸库名称")
    private String repositoryName;

    @ApiModelProperty(value = "人脸库颜色 1-灰色 2-橙色")
    private Integer repositoryColor;

    @ApiModelProperty(value = "相似度")
    private Integer similarity;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "是否布控(可忽略)")
    private Integer surveillance;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "人员绑定库产生的 id")
    private Long uid;

    @ApiModelProperty(value = "国籍(可忽略)")
    private String nationality;


}
