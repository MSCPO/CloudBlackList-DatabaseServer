package cn.etstmc.cloudblacklist.command;

import java.util.Scanner;

public class CommandManager {
    private final Scanner scanner = new Scanner(System.in);

    public CommandManager () {
        new Thread(() -> {
            Thread.currentThread().setName("COMMAND-THREAD");
            while (true) {
                run();
            }
        }).start();
    }

    public void run () {
        System.out.print("> ");
        switch (scanner.nextLine()) {
            case "exit" -> System.exit(0);
            case "help" -> System.out.println("help 查看帮助\nexit 关闭服务端");
            default -> System.out.println("未知命令，使用help获取帮助");
        }
    }
}
