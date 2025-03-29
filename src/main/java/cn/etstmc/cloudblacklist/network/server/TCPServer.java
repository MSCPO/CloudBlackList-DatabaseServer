package cn.etstmc.cloudblacklist.network.server;

import cn.etstmc.cloudblacklist.Kernel;
import cn.etstmc.cloudblacklist.api.network.PacketListener;
import cn.etstmc.cloudblacklist.network.Packet;
import cn.etstmc.cloudblacklist.network.PacketType;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cn.etstmc.cloudblacklist.Kernel.logger;

@ChannelHandler.Sharable
public class TCPServer extends ChannelInboundHandlerAdapter {
    private final Map<String, ChannelHandlerContext> CHANNELS = new ConcurrentHashMap<>();
    private final Map<Class<? extends PacketType>, List<PacketListener<? extends Packet>>> listeners = new ConcurrentHashMap<>();

    public void registerPacketListener (Class<? extends PacketType> clazz, PacketListener<? extends Packet> listener) {
        if (clazz == null || listener == null) {
            logger.warn("参数clazz或listener不能为null！");
        }
        List<PacketListener<? extends Packet>> list = listeners.computeIfAbsent(clazz, k -> new ArrayList<>());
        list.add(listener);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Packet packet) {
            PacketType pt = Kernel.packetManager.getPacketType(packet.getType());
            if (pt == null) return;
            Class<? extends PacketType> type = pt.getClass();
            if (listeners.get(type) == null) return;
            List<PacketListener<? extends Packet>> list = listeners.get(type);
            if (list == null || list.isEmpty()) {
                return;
            }
            for (PacketListener<? extends Packet> packetListener : list) {
                packetListener.checkPacket(packet, ctx);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CHANNELS.put(ctx.channel().remoteAddress().toString(), ctx);
        logger.info("客户端已上线，IP {}", ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端已下线，IP {}", ctx.channel().remoteAddress());
        CHANNELS.remove(ctx.channel().remoteAddress().toString());
    }

    public Map<String, ChannelHandlerContext> getChannels () {
        return CHANNELS;
    }
}
