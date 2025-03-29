package cn.etstmc.cloudblacklist;

import cn.etstmc.cloudblacklist.api.network.server.ServerNetworkManager;
import cn.etstmc.cloudblacklist.command.CommandManager;
import cn.etstmc.cloudblacklist.network.PacketManager;
import cn.etstmc.cloudblacklist.network.server.ServerNManagerInstant;
import cn.etstmc.cloudblacklist.utils.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Kernel {
    public static Logger logger;
    public static ServerNetworkManager networkManager;
    public static File dataFolder;
    public static PacketManager packetManager;
    public static Configuration config;
    public static CommandManager commandManager;

    public void onEnable () {
        long start = System.currentTimeMillis();
        logger = LoggerFactory.getLogger(this.getClass());
        logger.info("正在加载Kernel Class：{}", getClass().getName());
        networkManager = new ServerNManagerInstant("0.0.0.0", 35565);
        packetManager = new PacketManager();
        config = new Configuration();
        new WhenClose();
        while (!networkManager.getSocket().isStarted()) {
            Thread.onSpinWait();
        }
        //
        Register.init();
        NetworkInit.init();
        //
        logger.info("加载完成，耗时 {} ms", System.currentTimeMillis() - start);
        commandManager = new CommandManager();
    }

    public void onDisable () {
        logger.info("正在关闭 MSCPO-CloudBlackList-DatabaseServer");
    }
}
