package com.edm.camera.advise.camera;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Edm
 * @create 2020-08-08 0:52
 * @email edisonmiao@icloud.com
 */
@Slf4j
@Configuration
public class TcpServerNetty implements InitializingBean, DisposableBean {
    @Autowired
    private ServerBootstrap serverBootstrap;
    @Autowired
    private EventLoopGroup bossGroup;
    @Autowired
    private EventLoopGroup workGroup;
    @Autowired
    private TcpInboundHandler tcpInboundHandler;
    @Autowired
    private TcpOutboundHandler tcpOutboundHandler;
    @Value("${tcp.port}")
    private int tcpPort;
    private static final String HOST = "0.0.0.0";
    @Bean
    public ServerBootstrap serverBootstrap() {
        return new ServerBootstrap();
    }
    @Bean
    public EventLoopGroup bossGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public EventLoopGroup workGroup() {
        return new NioEventLoopGroup();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        serverBootstrap.group(bossGroup, workGroup)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 127)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast("bytesDecoder", new ByteArrayDecoder());
                        socketChannel.pipeline().addLast("bytesEncoder", new ByteArrayEncoder());
                        socketChannel.pipeline().addLast(new IdleStateHandler(0,0,20), tcpInboundHandler);
                        socketChannel.pipeline().addLast(tcpOutboundHandler);
                    }
                });
        ChannelFuture tcp = serverBootstrap.bind(HOST, tcpPort).sync();
        if (tcp.isSuccess()) {
            log.info("==============Tcp Netty Service启动成功==============端口号为：{}", tcpPort);
        }
    }

    @Override
    public void destroy() throws Exception {
        bossGroup.shutdownGracefully().syncUninterruptibly();
        workGroup.shutdownGracefully().syncUninterruptibly();
        log.info("==============Tcp Netty Service关闭成功==============");
    }

    public String getChannelUrl(ChannelHandlerContext ctx) {
        String socketString = ctx.channel().remoteAddress().toString();
        int colonAt = socketString.indexOf(":");
        return socketString.substring(1, colonAt);
    }


}
