package com.edm.camera.commons.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

/**
 * @author Edm
 * @create 2020-08-04 23:30
 * @email edisonmiao@icloud.com
 */
@Configuration
public class ActiveMQConfig {
    @Value("${spring.activemq.user}")
    private String mqUsername;

    @Value("${spring.activemq.password}")
    private String mqPassword;

    @Value("${spring.activemq.broker-url}")
    private String mqUrl;

    @Value("${spring.activemq.close-timeout}")
    private String mqTimeout;

    @Value("${spring.activemq.send-timeout}")
    private String mqSendTimeout;

    @Value("${spring.activemq.non-blocking-redelivery}")
    private String mqRedelivery;

    @Value("${spring.jms.pub-sub-domain}")
    private String mqDomain;


    @Bean(name = "topic")
    public Destination getTopic() {
        return new ActiveMQTopic("mq.bfms.client.msg.topic");
    }
    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(mqUrl);
        activeMQConnectionFactory.setPassword(mqPassword);
        activeMQConnectionFactory.setUserName(mqUsername);
        activeMQConnectionFactory.setCloseTimeout(Integer.parseInt(mqTimeout.split("s")[0]));
        activeMQConnectionFactory.setAlwaysSyncSend(true);
        activeMQConnectionFactory.setMaxThreadPoolSize(2);
        activeMQConnectionFactory.setSendTimeout(Integer.parseInt(mqSendTimeout));
        activeMQConnectionFactory.setNonBlockingRedelivery(mqRedelivery.equals("true"));
        return activeMQConnectionFactory;
    }
    @Bean(name="topicListenerContainerFactory")
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> topicListenerContainerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(mqDomain.equals("true"));
        return factory;
    }

    @Bean(name="queueListenerContainerFactory")
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> queueListenerContainerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(mqDomain.equals("false"));
        return factory;
    }
}
