package cn.etstmc.cloudblacklist;

import cn.etstmc.cloudblacklist.api.network.server.ServerNetworkManager;
import cn.etstmc.cloudblacklist.network.PacketManager;
import cn.etstmc.cloudblacklist.network.client.ClientNMangerInstant;

import java.io.File;
import java.util.logging.Logger;

public class Kernel {
    public static Logger logger;
    public static ServerNetworkManager networkManager;
    public static File dataFolder;
    public static PacketManager packetManager;

    public void onEnable () {
        long start = System.currentTimeMillis();
        logger.info("正在加载Kernel Class：" + getClass().getName());
        networkManager =
        packetManager = new PacketManager();
        //
        Register.init();
        NetworkInit.init();
        //
        logger.info("加载完成，耗时 " + (System.currentTimeMillis() - start) + " ms");
    }

    public void onDisable () {
        logger.info("正在关闭 MSCPO-CloudBlackList-DatabaseServer");
    }
}
