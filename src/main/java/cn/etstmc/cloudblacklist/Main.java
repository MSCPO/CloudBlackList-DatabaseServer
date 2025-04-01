package cn.etstmc.cloudblacklist;

import static cn.etstmc.cloudblacklist.utils.ThreadUtils.setThreadName;

public class Main {
    public static Kernel kernel;

    public static void main (String[] args) {
        setThreadName("MAIN-THREAD");
        kernel = new Kernel();
        kernel.onEnable();
    }
}
