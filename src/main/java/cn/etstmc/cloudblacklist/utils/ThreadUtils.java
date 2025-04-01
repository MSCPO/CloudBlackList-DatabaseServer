package cn.etstmc.cloudblacklist.utils;

public class ThreadUtils {
    public static void setThreadName (String name) {
        Thread.currentThread().setName(name);
    }
}
