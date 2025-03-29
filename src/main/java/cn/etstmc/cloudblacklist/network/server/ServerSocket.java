package cn.etstmc.cloudblacklist.network.server;

import cn.etstmc.cloudblacklist.network.PacketDecoder;
import cn.etstmc.cloudblacklist.network.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import static cn.etstmc.cloudblacklist.Kernel.logger;

public class ServerSocket {
    private final String host;
    private final int port;
    private final TCPServer server;

    public ServerSocket (String host, int port) {
        this.host = host;
        this.port = port;
        this.server = new TCPServer();
    }

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            socketChannel.pipeline().addLast(new PacketDecoder());
                            socketChannel.pipeline().addLast(new PacketEncoder());
                            socketChannel.pipeline().addLast(server);
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = serverBootstrap.bind(host, port).sync();
            logger.info("Netty基础框架加载完成！");
            logger.info("服务端已启动，监听 {}:{}", host, port);
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public TCPServer getServer() {
        return server;
    }
}
