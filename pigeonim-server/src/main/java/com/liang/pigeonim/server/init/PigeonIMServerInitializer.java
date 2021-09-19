package com.liang.pigeonim.server.init;

import com.liang.pigeonim.common.protocol.PigeonIMRequestProto;
import com.liang.pigeonim.server.handle.PigeonIMServerHandle;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/4 6:33 PM
 */
public class PigeonIMServerInitializer extends ChannelInitializer<Channel> {

    private final PigeonIMServerHandle pigeonIMServerHandle = new PigeonIMServerHandle();

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                .addLast(new IdleStateHandler(10, 0, 0))
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(PigeonIMRequestProto.PigeonIMReqProcotol.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(pigeonIMServerHandle);
    }
}
