package cn.etstmc.cloudblacklist;

public class Main {
    public static Kernel kernel;

    public static void main (String[] args) {
        Thread.currentThread().setName("MAIN-THREAD");
        kernel = new Kernel();
        kernel.onEnable();
    }
}
