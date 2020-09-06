package com.edm.camera.controller;

import com.alibaba.fastjson.JSON;
import com.edm.camera.dto.FaceLogDataMQ;
import com.edm.camera.request.ListLockerRequest;
import com.edm.camera.response.ApiResult;
import com.edm.camera.service.ILockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

/**
 * @author Edm
 * @create 2020-08-05 0:14
 * @email edisonmiao@icloud.com
 */
@RestController
@RequestMapping("/locker")
public class LockerController {
    @Autowired
    private ILockerService lockerService;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination topic;
    @PostMapping("/listLocker")
    public ApiResult listLocker(ListLockerRequest request) {
        return ApiResult.ok(lockerService.listLocker(request));
    }

    @PostMapping("/test")
    public ApiResult test(@RequestBody FaceLogDataMQ mq) {
        jmsTemplate.convertAndSend(topic, JSON.toJSONString(mq));
        return ApiResult.ok();
    }

}
