package com.edm.camera.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @Author: Edm
 * @Date: 8/23/2019 2:43 PM
 */
@RequiredArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    FAIL(1),
    SERVICE_BUSIED(500, "服务器繁忙"),
    FACE_LOG_INFO_EMPTY(1001, "MQ解析为空"),
    NO_TOKEN_INFO(1014, "没有此Token信息请联系管理员"),
    NO_USER_INFO(1002, "没有用户信息"),
    LOCKER_OFF_LINE(1003, "锁已经离线"),
    CAMERA_NO_BIND_LOCK(1004, "摄像头没有绑定锁"),
    WRONG_ACCOUNT_PASSWORD(1005, "账号密码错误"),
    IS_BAN(1006, "账号被禁用"),
    SESSION_KEY_EXPIRED(1007, "微信SESSION_KEY过期，请重新授权"),
    REQUEST_METHOD_ERROR(1008, "请求方式错误"),
    USERNAME_REPEATED(1008, "用户名重复"),
    VALID_PARAMETER(1009, "少传左参数啊衰仔"),
    NO_LOGIN_BACKEND(1010, "后台冇登陆啊衰仔"),
    NO_MISSION_INFO(1011, "没有该任务信息，请检查任务id是否正确"),
    ADD_TOTAL_COURSE_PERIOD_ERROR(1012, "添加总课程数量不能小于1"),
    ORDER_COURSE_OVER(1013, "预约课程过多，还想上课的可以去报名");


    @Getter
    @NonNull
    private final Integer code;
    @Getter
    private String message;


}
