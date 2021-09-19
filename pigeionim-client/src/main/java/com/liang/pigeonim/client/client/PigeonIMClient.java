package com.liang.pigeonim.client.client;

import com.liang.pigeonim.client.entity.res.PigeonIMServerResVO;
import com.liang.pigeonim.client.handle.PigeonIMClientHandle;
import com.liang.pigeonim.common.constant.Constant;
import com.liang.pigeonim.common.protocol.PigeonIMRequestProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/7 4:31 PM
 */
@Component
@Slf4j
public class PigeonIMClient {

    private EventLoopGroup group = new NioEventLoopGroup(0, new DefaultThreadFactory("pigeonim-work"));

    @Value("${pigeonim.user.id}")
    private long userId;

    @Value("${pigeonim.user.username}")
    private String userName;

    @Value("${pigeonim.reconnect.count}")
    private int reconnectCount;

    private SocketChannel channel;

    /**
     * 重试次数
     */
    private int errorCount;

    @PostConstruct
    public void start() {

        PigeonIMServerResVO.ServerInfo pigeonimServer = PigeonIMServerResVO.ServerInfo.builder().ip("127.0.0.1").pigeonIMServerPort(8080).build();

        // 启动客户端
        startClient(pigeonimServer);

        // 向服务器注册
        loginPigeonIMServer();
    }


    /**
     * 启动客户端
     * @param pigeonimServer
     */
    private void startClient(PigeonIMServerResVO.ServerInfo pigeonimServer) {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new PigeonIMClientHandle());
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.connect(pigeonimServer.getIp(), pigeonimServer.getPigeonIMServerPort()).sync();
        }catch (Exception e) {
            errorCount++;
            if (errorCount >= reconnectCount) {
                log.error("连接失败次数达到上限: {}", reconnectCount);
            }
            log.error("连接失败...", e);
        }
        if (channelFuture.isSuccess()) {
            log.info("成功启动客户端...");
        }
        channel = (SocketChannel) channelFuture.channel();
    }


    /**
     * 向服务器注册
     */
    private void loginPigeonIMServer() {
        PigeonIMRequestProto.PigeonIMReqProcotol login = PigeonIMRequestProto.PigeonIMReqProcotol.newBuilder()
                .setRequestId(userId)
                .setReqMsg(userName)
                .setType(Constant.MessageTpye.LOGIN)
                .build();
        ChannelFuture future = channel.writeAndFlush(login);
        future.addListener((ChannelFutureListener) channelFuture -> log.info("成功向服务器注册..."));
    }
}
