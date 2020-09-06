package com.edm.camera.activemq;

import com.alibaba.fastjson.JSON;
import com.edm.camera.commons.enums.ErrorCode;
import com.edm.camera.commons.exception.BusinessException;
import com.edm.camera.dto.FaceLogDataMQ;
import com.edm.camera.dto.FaceLogDataVO;
import com.edm.camera.dto.FaceLogMQ;
import com.edm.camera.dto.SimilarFaceDTO;
import com.edm.camera.service.IScanFaceLogService;
import com.edm.camera.utils.RedisUtils;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Edm
 * @create 2020-08-04 23:37
 * @email edisonmiao@icloud.com
 */
@Component
@Slf4j
public class MQConsumer {
    @Autowired
    private IScanFaceLogService scanFaceLogService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private Map<Integer, Channel> channelMap;
    @Autowired
    private RedisUtils redisUtils;

    @JmsListener(destination = "mq.bfms.client.msg.topic", containerFactory = "topicListenerContainerFactory")
    public void receiveQueue(String message) {
        log.info("人脸流水mq入口，信息为：{}", message);
        try {
            FaceLogDataMQ dataDTO = JSON.parseObject(message, FaceLogDataMQ.class);
            FaceLogMQ data = dataDTO.getInfo();
            Optional.ofNullable(data).orElseThrow(() -> new BusinessException(ErrorCode.FACE_LOG_INFO_EMPTY));
            FaceLogDataVO faceLogDataVO = new FaceLogDataVO();
            faceLogDataVO.setScenePic(data.getPictureUrl());
            faceLogDataVO.setFacePic(data.getFaceImageUrl());
            List<SimilarFaceDTO> similarFaces = data.getSimilarFaces();
            if (!similarFaces.isEmpty()) {
                data.setSimilar(1);
                faceLogDataVO.setIsBlack(1);
                faceLogDataVO.setName(similarFaces.get(0).getName());
                faceLogDataVO.setSimilarity(similarFaces.get(0).getSimilarity());
                faceLogDataVO.setLockerStatus("开锁");
                faceLogDataVO.setFaceLibraryPic(similarFaces.get(0).getFaceImageUrl());
                Object lockerIdO = redisUtils.get("camera:" + data.getChannelId());
                if (lockerIdO == null) {
                    log.error("摄像头没有绑定");
                    throw new BusinessException(ErrorCode.CAMERA_NO_BIND_LOCK);
                }
                String lockerId = lockerIdO.toString();
                Object md5O = redisUtils.hget("locker:" + lockerId, "md5");
                String ip = redisUtils.hget("locker:" + lockerId, "ip").toString();
                if (md5O == null) {
                    log.error("锁已经掉线ip为:{},id为:{}", ip, lockerId);
                    throw new BusinessException(ErrorCode.LOCKER_OFF_LINE);
                }
                String md5 = md5O.toString();
                String lockMessage = "{\"ser\":\"0\",\"instruction\":\"open\"}";
                log.info("发送给ip为:{},id为:{},信息为:{}", ip, lockerId, lockMessage);
                channelMap.get(Integer.parseInt(md5)).writeAndFlush(lockMessage.getBytes());
            } else {
                faceLogDataVO.setLockerStatus("不开锁");
                faceLogDataVO.setIsBlack(0);
            }
            messagingTemplate.convertAndSend("/topic/faceLogStream", JSON.toJSONString(faceLogDataVO));
            //保存信息
            scanFaceLogService.saveFaceLogData(data);
        } catch (BusinessException e) {
            log.error("业务错误", e);
        } catch (Exception e) {
            log.error("MQ错误", e);
        }
    }

    @JmsListener(destination = "queue-1", containerFactory = "queueListenerContainerFactory")
    public void receiveQueue1(String message) {
        log.info(message);
    }

}
