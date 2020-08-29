package com.edm.camera.advise.camera;

import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Edm
 * @create 2020-08-08 1:00
 * @email edisonmiao@icloud.com
 */
@Component
@Slf4j
@ChannelHandler.Sharable
public class TcpOutboundHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ctx.writeAndFlush(msg).addListener((ChannelFutureListener) future -> {
            log.info("下发信息成功");
        });
    }
}
