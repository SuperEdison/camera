package com.edm.camera.commons.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Edm
 * @create 2020-06-20 17:32
 * @email edisonmiao@icloud.com
 */
@Data
public class PageRequest implements Serializable {
    @NotNull(message = "第几页")
    private Integer pageNum;
    @ApiModelProperty("一个有多少条记录")
    @NotNull(message = "页面大小不能为空")
    private Integer pageSize;
    @ApiModelProperty("排序方式默认记录创建时间")
    private String orderByColumn;
    @ApiModelProperty(hidden = true)
    private Integer offset;

    public Integer getPageNum() {
        return pageNum == null || pageNum <= 0 ? 1 : pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null || pageSize <= 0 ? 10 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public Integer getOffset() {
        return (getPageNum() - 1) * getPageSize();
    }

}
