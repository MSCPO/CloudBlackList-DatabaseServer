package cn.etstmc.cloudblacklist.network.packetlisteners.serverbound;

import cn.etstmc.cloudblacklist.api.network.PacketListener;
import cn.etstmc.cloudblacklist.network.client.packets.ServerBoundHandShakePacket;
import cn.etstmc.cloudblacklist.network.server.packets.ClientBoundHandShakePacket;
import io.netty.channel.ChannelHandlerContext;

public class HandShakePacketListener extends PacketListener<ServerBoundHandShakePacket> {
    @Override
    public void onPacket(ServerBoundHandShakePacket packet, ChannelHandlerContext ctx) {
        System.out.println(packet.getBody());
        System.out.println(packet.getBukkitVersion());
        System.out.println(packet.getPluginVersion());
        System.out.println(packet.getServerName());
        System.out.println(packet.getServerVersion());
        ctx.writeAndFlush(new ClientBoundHandShakePacket(true, "1.0"));
    }
}
