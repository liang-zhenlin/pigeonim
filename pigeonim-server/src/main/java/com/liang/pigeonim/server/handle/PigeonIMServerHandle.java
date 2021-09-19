package com.liang.pigeonim.server.handle;

import com.liang.pigeonim.common.constant.Constant;
import com.liang.pigeonim.common.protocol.PigeonIMRequestProto;
import com.liang.pigeonim.common.util.SessionSocketUtil;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/6 1:28 PM
 */
@ChannelHandler.Sharable
@Slf4j
public class PigeonIMServerHandle extends SimpleChannelInboundHandler<PigeonIMRequestProto.PigeonIMReqProcotol> {

    private static ApplicationContext context;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, PigeonIMRequestProto.PigeonIMReqProcotol pigeonIMReqProcotol) throws Exception {
        log.info("读取到的消息 message=【{}】", pigeonIMReqProcotol.toString());
        if (pigeonIMReqProcotol.getType() == Constant.MessageTpye.LOGIN) {
            SessionSocketUtil.put(pigeonIMReqProcotol.getRequestId(), (NioSocketChannel) channelHandlerContext.channel());
            SessionSocketUtil.saveSession(pigeonIMReqProcotol.getRequestId(), pigeonIMReqProcotol.getReqMsg());
            log.info("client {} online success", pigeonIMReqProcotol.getReqMsg());
        }

        if (pigeonIMReqProcotol.getType() == Constant.MessageTpye.PING) {
            channelHandlerContext.attr(AttributeKey.valueOf("readerTime")).set(System.currentTimeMillis());
            // 向客户端响应
            PigeonIMRequestProto.PigeonIMReqProcotol hearBeat = context.getBean("heartBeat", PigeonIMRequestProto.PigeonIMReqProcotol.class);
            channelHandlerContext.writeAndFlush(hearBeat).addListener((ChannelFutureListener) future -> {
                if (!future.isSuccess()) {
                    log.error("IO error, close Channel");
                    future.channel().close();
                }
            });
        }
    }
}
