package com.edm.camera.commons.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Edm
 * @create 2020-08-07 0:27
 * @email edisonmiao@icloud.com
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/faceEndpoint").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //注册广播消息代理和点对点代理
        registry.enableSimpleBroker("/topic", "/queue"); //@注解3
        //设置点对点代理订阅前缀
        registry.setUserDestinationPrefix("/queue"); //@注解4
    }
}
