package cn.etstmc.cloudblacklist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static cn.etstmc.cloudblacklist.Main.kernel;

public class WhenClose {
    private static final Logger log = LoggerFactory.getLogger(WhenClose.class);

    public WhenClose () {
        Runtime.getRuntime().addShutdownHook(new Thread (() -> {
            Thread.currentThread().setName("SHUTDOWN-THREAD");
            log.info("正在关闭服务端……");
            Kernel.networkManager.getSocket().kill();
            kernel.onDisable();
        }));
    }
}
