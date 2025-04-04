package cn.etstmc.cloudblacklist;

import cn.etstmc.cloudblacklist.network.client.packets.ServerBoundDatabaseCheckPacket;
import cn.etstmc.cloudblacklist.network.client.packets.ServerBoundHandShakePacket;
import cn.etstmc.cloudblacklist.network.packetlisteners.serverbound.DatabaseCheckPacketListener;
import cn.etstmc.cloudblacklist.network.packetlisteners.serverbound.HandShakePacketListener;
import cn.etstmc.cloudblacklist.network.packettyps.DataBasePacketType;
import cn.etstmc.cloudblacklist.network.packettyps.HandShakePacketType;
import cn.etstmc.cloudblacklist.network.server.packets.ClientBoundHandShakePacket;

public class NetworkInit {
    public static void init () {
        //
        Kernel.packetManager.registerType(new HandShakePacketType(), HandShakePacketType.type);
        Kernel.packetManager.registerType(new DataBasePacketType(), DataBasePacketType.type);
        //
        Register.registerPacket(HandShakePacketType.class, ServerBoundHandShakePacket.class, 0);
        Register.registerPacket(HandShakePacketType.class, ClientBoundHandShakePacket.class, 1);
        Register.registerPacket(DataBasePacketType.class, ServerBoundDatabaseCheckPacket.class, 0);
        //
        Kernel.networkManager.getServer().registerPacketListener(HandShakePacketType.class, new HandShakePacketListener());
        Kernel.networkManager.getServer().registerPacketListener(DataBasePacketType.class, new DatabaseCheckPacketListener());
        //
    }
}
