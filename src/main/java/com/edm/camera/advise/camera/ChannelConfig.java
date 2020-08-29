package com.edm.camera.advise.camera;

import io.netty.channel.Channel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Edm
 * @create 2020-08-27 22:53
 * @email edisonmiao@icloud.com
 */
@Configuration
public class ChannelConfig {
    @Bean
    public Map<Integer, Channel> channelMap() {
        return new ConcurrentHashMap<>();
    }
}
