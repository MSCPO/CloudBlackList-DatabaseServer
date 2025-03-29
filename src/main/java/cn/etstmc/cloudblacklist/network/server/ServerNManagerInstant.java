package cn.etstmc.cloudblacklist.network.server;

import cn.etstmc.cloudblacklist.api.network.server.ServerNetworkManager;
import cn.etstmc.cloudblacklist.utils.ExceptionUtils;

import static cn.etstmc.cloudblacklist.Kernel.logger;

public class ServerNManagerInstant implements ServerNetworkManager {
    private final ServerSocket socket;


    public ServerNManagerInstant (String host, int port) {
        this.socket = new ServerSocket(host, port);
        new Thread (() -> {
            try {
                Thread.currentThread().setName("SERVER-THREAD");
                socket.start();
            } catch (Exception e) {
                logger.warn("发生异常：{}", ExceptionUtils.getStackTrace(e));
            }
        }).start();
    }
    @Override
    public ServerSocket getSocket() {
        return socket;
    }

    @Override
    public TCPServer getServer() {
        return socket.getServer();
    }
}
