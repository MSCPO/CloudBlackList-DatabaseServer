package cn.etstmc.cloudblacklist.network.packetlisteners.serverbound;

import cn.etstmc.cloudblacklist.Kernel;
import cn.etstmc.cloudblacklist.RunningData;
import cn.etstmc.cloudblacklist.api.minecraftserver.MinecraftServerConnection;
import cn.etstmc.cloudblacklist.api.network.PacketListener;
import cn.etstmc.cloudblacklist.network.client.packets.ServerBoundHandShakePacket;
import cn.etstmc.cloudblacklist.network.server.packets.ClientBoundHandShakePacket;
import io.netty.channel.ChannelHandlerContext;

public class HandShakePacketListener extends PacketListener<ServerBoundHandShakePacket> {
    @Override
    public void onPacket(ServerBoundHandShakePacket packet, ChannelHandlerContext ctx) {
        Kernel.logger.info("收到客户端 {} 的握手数据包，正在校验……", ctx.channel().remoteAddress());
        Kernel.logger.info("客户端 {} 的服务器名为 {} \n版本 {} \n服务器类型 {} \n 运行模式 {}", ctx.channel().remoteAddress(), packet.getServerName(),
                packet.getPluginVersion(),
                packet.getServerType(),
                packet.getMode());
        RunningData.addConnection(new MinecraftServerConnection(packet.getServerName(),
                packet.getServerType(),
                packet.getMode(),
                packet.getServerVersion(),
                packet.getPluginVersion(),
                packet.getApiVersion(),
                ctx));
        ctx.writeAndFlush(new ClientBoundHandShakePacket(true, "1.0"));
    }
}
