package com.edm.camera.advise.camera;

import com.alibaba.fastjson.JSON;
import com.edm.camera.dto.LockerRequest;
import com.edm.camera.utils.RedisUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Edm
 * @create 2020-08-08 0:59
 * @email edisonmiao@icloud.com
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class TcpInboundHandler extends SimpleChannelInboundHandler<byte[]> {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private Map<Integer, Channel> channelMap;
    @Autowired
    private TcpServerNetty tcpServerNetty;
    private static final String LOCK_PREFIX = "locker:";
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Channel channel = ctx.channel();
        int md5 = channel.hashCode();
        channelMap.put(md5, channel);
        log.info("有设备上线md5为：{}", md5);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        Channel channel = ctx.channel();
        int md5 = channel.hashCode();
        channelMap.remove(md5);
        log.info("有设备离线md5为：{}", md5);
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        Channel channel = ctx.channel();
        String socketString = channel.remoteAddress().toString();
        int md5 = channel.hashCode();
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                log.error("READER_IDLE 读超时 ip为:{},md5为：{} ", socketString, md5);
                ctx.disconnect();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.error("WRITER_IDLE 写超时 ip为:{},md5为：{} ", socketString, md5);
                ctx.disconnect();
            } else if (event.state() == IdleState.ALL_IDLE) {
                log.error("ALL_IDLE 总超时 ip为:{},md5为：{} ", socketString, md5);
                ctx.disconnect();
            }

        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        Channel channel = ctx.channel();
        String message = new String(msg);
        String md5 = String.valueOf(channel.hashCode());
        LockerRequest lockerRequest = JSON.parseObject(message, LockerRequest.class);
        int id = Integer.parseInt(lockerRequest.getId());
        if (!channelMap.containsKey(id)) {
            channelMap.put(id, channel);
        }
        String ip = tcpServerNetty.getChannelUrl(ctx);
        log.info("来自设备ip为:{},md5为:{} id為{}的信息",ip, channel.hashCode(),id);
        log.info("开始JSON解析锁传来的信息,并且延迟监听器");
        redisUtils.hset(LOCK_PREFIX + id, "bluetooth", lockerRequest.getBluetooth());
        redisUtils.hset(LOCK_PREFIX + id, "doorStatus", lockerRequest.getDoorStatus());
        redisUtils.hset(LOCK_PREFIX + id, "lockStatus", lockerRequest.getLockStatus());
        redisUtils.hset(LOCK_PREFIX + id, "electricity", lockerRequest.getElectricity());
        redisUtils.hset(LOCK_PREFIX + id, "ip", ip);
        redisUtils.hset(LOCK_PREFIX + id, "serial", 0);
        redisUtils.hset(LOCK_PREFIX + id, "md5", md5);
        redisUtils.expire(LOCK_PREFIX + id, 15);
        ctx.channel().writeAndFlush(("{\"code\":\"0\",\"ser\":\"" + lockerRequest.getSerial() + "\"}").getBytes());
    }

}