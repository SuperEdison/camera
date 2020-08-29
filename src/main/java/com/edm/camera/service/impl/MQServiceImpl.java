package com.edm.camera.service.impl;

import com.edm.camera.service.MQService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * @author Edm
 * @create 2020-08-06 21:46
 * @email edisonmiao@icloud.com
 */
public class MQServiceImpl implements MQService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private ActiveMQQueue activeMQQueue;

    @Override
    public void sendMessage() {
        jmsMessagingTemplate.convertAndSend(activeMQQueue, "aaa");
    }
}
