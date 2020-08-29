package com.edm.camera.request;

import lombok.Data;

/**
 * @author Edm
 * @create 2020-08-06 22:06
 * @email edisonmiao@icloud.com
 */
@Data
public class ListLockerRequest {
    private String doorStatus;
    private String lockerStatus;
    private String ip;
}
