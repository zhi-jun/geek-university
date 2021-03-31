package week03.answer03;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class SampleChannelInitializer extends io.netty.channel.ChannelInitializer<SocketChannel> {


    private static final SampleChannelInboundHandler SERVER_HANDLER = new SampleChannelInboundHandler();


    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(SERVER_HANDLER);
    }
}
