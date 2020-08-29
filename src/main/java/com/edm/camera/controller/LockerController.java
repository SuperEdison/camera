package com.edm.camera.controller;

import com.edm.camera.request.ListLockerRequest;
import com.edm.camera.response.ApiResult;
import com.edm.camera.service.ILockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

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
    public ApiResult listLocker(@RequestBody ListLockerRequest request) {
        return ApiResult.ok(lockerService.listLocker(request));
    }

    @GetMapping("/test")
    public ApiResult test() {
        String message = "";
        jmsTemplate.convertAndSend(topic, "{\n" +
                "\t\"method\": \"bfms.notifyFaceInfo\",\n" +
                "\t\"info\": {\n" +
                "\t\t\"appearTimes\": \"0\",\n" +
                "\t\t\"beginTime\": \"1598277363\",\n" +
                "\t\t\"channelId\": \"1000003$1$0$0\",\n" +
                "\t\t\"channelName\": \"人脸识别\",\n" +
                "\t\t\"endTime\": \"1598277363\",\n" +
                "\t\t\"faceImageUrl \": \"http: //192.168.0.161:50000/3977a1cb-b0e8-11e5-b2c3-04d4c4f1acaf/20160105/3/dsf_5aeb136b-b383-11e5-9d32-04 d4c4f1acaf_32418914_32575512.jpg \",\n" +
                "\t\t\"hited \": \"1\",\n" +
                "\t\t\"pictureUrl\": \"http://192.168.0.161:50000/3977a1cb-b0e8-11e5-b2c3-04d4c4f1acaf/20160105/3/dsf_5aeb136b-b383-11e5-9d32-04 d4c4f1acaf_32307514_32418914.jpg \",\n" +
                "\t\t\"recAge \": \"36 \",\n" +
                "\t\t\"recBeard \": \"2 \",\n" +
                "\t\t\"recEmotion \": \"6 \",\n" +
                "\t\t\"recEye \": \"0 \",\n" +
                "\t\t\"recFringe \": \"0 \",\n" +
                "\t\t\"recGender \": \"1\",\n" +
                "\t\t\"recGlasses \": \"1\",\n" +
                "\t\t\"recMask \": \"2 \",\n" +
                "\t\t\"recMouth \": \"0\",\n" +
                "\t\t\"serviceCode\": \"4001\",\n" +
                "\t\t\"similarFaces\": [{\n" +
                "\t\t\t\"birthday\": \"\",\n" +
                "\t\t\t\"contactNum\": \"\",\n" +
                "\t\t\t\"faceImageUrl\": \"http://192.168.0.161:80/upload/face/person/1/library_b20755c6754e4c1e98fc01ad6a006eb1.jpg\",\n" +
                "\t\t\t\"gender\": \"1\",\n" +
                "\t\t\t\"idCode \": \"\",\n" +
                "\t\t\t\"name \": \"老惠 \",\n" +
                "\t\t\t\"personId \": \"1234567 \",\n" +
                "\t\t\t\"personTypeId \": \"1 \",\n" +
                "\t\t\t\"personTypeName \": \"学生 \",\n" +
                "\t\t\t\"repositoryColor \": \"1 \",\n" +
                "\t\t\t\"repositoryId \": \"8 \",\n" +
                "\t\t\t\"repositoryName \": \"学生 \",\n" +
                "\t\t\t\"similarity \": \"99 \",\n" +
                "\t\t\t\"surveillance \": \"0\",\n" +
                "\t\t\t\"uid \": \"11 \"\n" +
                "\t\t}]\n" +
                "\t}\n" +
                "}");return ApiResult.ok();
    }

    @GetMapping("/test1")
    public ApiResult test1() {
        String message = "";
        jmsTemplate.convertAndSend(topic, "{\n" +
                "\t\"method\": \"bfms.notifyFaceInfo\",\n" +
                "\t\"info\": {\n" +
                "\t\t\"appearTimes\": \"0\",\n" +
                "\t\t\"beginTime\": \"1598277363\",\n" +
                "\t\t\"channelId\": \"1000003$1$0$1\",\n" +
                "\t\t\"channelName\": \"人脸识别\",\n" +
                "\t\t\"endTime\": \"1598277363\",\n" +
                "\t\t\"faceImageUrl \": \"http: //192.168.0.161:50000/3977a1cb-b0e8-11e5-b2c3-04d4c4f1acaf/20160105/3/dsf_5aeb136b-b383-11e5-9d32-04 d4c4f1acaf_32418914_32575512.jpg \",\n" +
                "\t\t\"hited \": \"1\",\n" +
                "\t\t\"pictureUrl\": \"http://192.168.0.161:50000/3977a1cb-b0e8-11e5-b2c3-04d4c4f1acaf/20160105/3/dsf_5aeb136b-b383-11e5-9d32-04 d4c4f1acaf_32307514_32418914.jpg \",\n" +
                "\t\t\"recAge \": \"36 \",\n" +
                "\t\t\"recBeard \": \"2 \",\n" +
                "\t\t\"recEmotion \": \"6 \",\n" +
                "\t\t\"recEye \": \"0 \",\n" +
                "\t\t\"recFringe \": \"0 \",\n" +
                "\t\t\"recGender \": \"1\",\n" +
                "\t\t\"recGlasses \": \"1\",\n" +
                "\t\t\"recMask \": \"2 \",\n" +
                "\t\t\"recMouth \": \"0\",\n" +
                "\t\t\"serviceCode\": \"4001\",\n" +
                "\t\t\"similarFaces\": [{\n" +
                "\t\t\t\"birthday\": \"\",\n" +
                "\t\t\t\"contactNum\": \"\",\n" +
                "\t\t\t\"faceImageUrl\": \"http://192.168.0.161:80/upload/face/person/1/library_b20755c6754e4c1e98fc01ad6a006eb1.jpg\",\n" +
                "\t\t\t\"gender\": \"1\",\n" +
                "\t\t\t\"idCode \": \"\",\n" +
                "\t\t\t\"name \": \"老惠 \",\n" +
                "\t\t\t\"personId \": \"1234567 \",\n" +
                "\t\t\t\"personTypeId \": \"1 \",\n" +
                "\t\t\t\"personTypeName \": \"学生 \",\n" +
                "\t\t\t\"repositoryColor \": \"1 \",\n" +
                "\t\t\t\"repositoryId \": \"8 \",\n" +
                "\t\t\t\"repositoryName \": \"学生 \",\n" +
                "\t\t\t\"similarity \": \"99 \",\n" +
                "\t\t\t\"surveillance \": \"0\",\n" +
                "\t\t\t\"uid \": \"11 \"\n" +
                "\t\t}]\n" +
                "\t}\n" +
                "}");return ApiResult.ok();
    }

}
