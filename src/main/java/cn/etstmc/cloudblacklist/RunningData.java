package cn.etstmc.cloudblacklist;

import cn.etstmc.cloudblacklist.api.minecraftserver.MinecraftServerConnection;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RunningData {
    private static final Map<String, MinecraftServerConnection> connections = new ConcurrentHashMap<>(), nameConnections = new ConcurrentHashMap<>();
    public static void addConnection (MinecraftServerConnection connection) {
        connections.put(connection.getCtx().channel().remoteAddress().toString(), connection);
        nameConnections.put(connection.getName(), connection);
    }

    public static MinecraftServerConnection getConnection (String s) {
        if (connections.containsKey(s)) return connections.get(s);
        if (nameConnections.containsKey(s)) return nameConnections.get(s);
        return null;
    }

    public static MinecraftServerConnection getConnection (ChannelHandlerContext ctx) {
        return connections.get(ctx.channel().remoteAddress().toString());
    }

    public static void removeConnection (MinecraftServerConnection connection) {
        connections.remove(connection.getCtx().channel().remoteAddress().toString());
        nameConnections.remove(connection.getName());
    }

    public static void removeConnection (ChannelHandlerContext ctx) {
        MinecraftServerConnection connection = connections.get(ctx.channel().remoteAddress().toString());
        removeConnection(connection);
    }

    public static void removeConnection (String name) {
        MinecraftServerConnection connection = nameConnections.get(name);
        removeConnection(connection);
    }
}
