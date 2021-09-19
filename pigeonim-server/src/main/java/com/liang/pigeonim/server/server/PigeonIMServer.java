package com.liang.pigeonim.server.server;

import com.liang.pigeonim.common.constant.Constant;
import com.liang.pigeonim.common.protocol.PigeonIMRequestProto;
import com.liang.pigeonim.common.util.SessionSocketUtil;
import com.liang.pigeonim.server.entity.req.SendMsgReqBO;
import com.liang.pigeonim.server.init.PigeonIMServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/4 6:22 PM
 */
@Slf4j
@Component
public class PigeonIMServer {

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup worker = new NioEventLoopGroup();

    @Value("${pigeonim.server.port}")
    private int nettyPort;

    /**
     * 启动 server
     * @throws InterruptedException
     */
    @PostConstruct
    public void start() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(nettyPort))
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new PigeonIMServerInitializer());
        ChannelFuture future = serverBootstrap.bind().sync();
        if (future.isSuccess()) {
            log.info("start pigeonIM server success...");
        }
    }


    /**
     * 关闭 server
     */
    @PreDestroy
    public void destory() {
        boss.shutdownGracefully().syncUninterruptibly();
        worker.shutdownGracefully().syncUninterruptibly();
        log.info("close pigeon server success...");
    }


    /**
     * 服务端推送消息
     * @param sendMsgReqBO
     */
    public void sendMessage(SendMsgReqBO sendMsgReqBO) {
        NioSocketChannel socketChannel = SessionSocketUtil.get(sendMsgReqBO.getUserId());
        if (null == socketChannel) {
            log.error("客户端 {} 不在线", sendMsgReqBO.getUserId());
            return;
        }
        PigeonIMRequestProto.PigeonIMReqProcotol procotol = PigeonIMRequestProto.PigeonIMReqProcotol.newBuilder()
                .setRequestId(sendMsgReqBO.getUserId())
                .setReqMsg(sendMsgReqBO.getMessage())
                .setType(Constant.MessageTpye.MSG)
                .build();
        ChannelFuture future = socketChannel.writeAndFlush(procotol);
        future.addListener((ChannelFutureListener) channelFuture -> log.info("服务端推送消息：{}", sendMsgReqBO.getMessage()));
    }
}
