package cn.etstmc.cloudblacklist.network.packetlisteners.serverbound;

import cn.etstmc.cloudblacklist.api.network.PacketListener;
import cn.etstmc.cloudblacklist.network.client.packets.ServerBoundDatabaseCheckPacket;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseCheckPacketListener extends PacketListener<ServerBoundDatabaseCheckPacket> {
    private static final Logger log = LoggerFactory.getLogger(DatabaseCheckPacketListener.class);

    @Override
    public void onPacket(ServerBoundDatabaseCheckPacket packet, ChannelHandlerContext ctx) {
        String playerName = packet.getPlayerName();
        String uuid = packet.getUuid();
        boolean onlineMode = packet.isOnlineMode();
        log.info("PlayerName: {} UUID: {} OnlineMode: {}", playerName, uuid, onlineMode);
    }
}
