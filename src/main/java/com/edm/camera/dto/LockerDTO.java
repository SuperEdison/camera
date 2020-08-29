package com.edm.camera.dto;

import lombok.Data;

/**
 * @author Edm
 * @create 2020-08-05 23:37
 * @email edisonmiao@icloud.com
 */
@Data
public class LockerDTO {
    private String doorStatus;
    private String ip;
    private String lockStatus;
    private String electricity;
}
