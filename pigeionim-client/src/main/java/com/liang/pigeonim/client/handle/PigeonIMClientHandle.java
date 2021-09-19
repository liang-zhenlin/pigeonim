package com.liang.pigeonim.client.handle;

import com.liang.pigeonim.common.constant.Constant;
import com.liang.pigeonim.common.protocol.PigeonIMResponseProto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/7 2:11 PM
 */
@ChannelHandler.Sharable
@Slf4j
public class PigeonIMClientHandle extends SimpleChannelInboundHandler<PigeonIMResponseProto.PigeonIMResProcotol> {

    private static ApplicationContext context;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, PigeonIMResponseProto.PigeonIMResProcotol pigeonIMResProcotol) throws Exception {
        if (Constant.MessageTpye.PING == pigeonIMResProcotol.getType()) {
            log.info("收到服务端心跳...");
            channelHandlerContext.attr(AttributeKey.valueOf("readerTime")).set(System.currentTimeMillis());
        }else {
            log.info("消息 【{}】", pigeonIMResProcotol.getResMsg());
        }
    }
}
