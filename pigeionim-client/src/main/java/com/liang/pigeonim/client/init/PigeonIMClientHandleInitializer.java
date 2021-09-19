package com.liang.pigeonim.client.init;

import com.liang.pigeonim.client.handle.PigeonIMClientHandle;
import com.liang.pigeonim.common.protocol.PigeonIMResponseProto;
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
 * @date 2021/9/7 4:38 PM
 */
public class PigeonIMClientHandleInitializer extends ChannelInitializer<Channel> {

    private final PigeonIMClientHandle pigeonIMClientHandle = new PigeonIMClientHandle();

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                .addLast(new IdleStateHandler(0, 10, 0))
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(PigeonIMResponseProto.PigeonIMResProcotol.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(pigeonIMClientHandle);
    }
}
