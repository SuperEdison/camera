package com.edm.camera.request;

import com.edm.camera.commons.entity.PageRequest;
import lombok.Data;

/**
 * @author Edm
 * @create 2020-08-27 20:33
 * @email edisonmiao@icloud.com
 */
@Data
public class FaceLogRequest extends PageRequest {
    private String beginStartTime;
    private String beginEndTime;
    private Integer similar;
}
