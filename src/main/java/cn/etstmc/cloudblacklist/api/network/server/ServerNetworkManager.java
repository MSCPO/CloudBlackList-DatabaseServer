package cn.etstmc.cloudblacklist.api.network.server;

import cn.etstmc.cloudblacklist.network.server.ServerSocket;
import cn.etstmc.cloudblacklist.network.server.TCPServer;

public interface ServerNetworkManager {
    ServerSocket getSocket ();
    TCPServer getServer ();
}
