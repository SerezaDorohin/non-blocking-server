package main.java.com.server;

import main.java.com.methods.ColorfulText;

import java.io.IOException;

public class StartServer {

    public static void main(String[] args) throws InterruptedException {
        try {
            Server.server();
        } catch (IOException e) {
            System.out.println();
            ColorfulText.setBackground("red");
            System.out.println();
            System.out.println();
            System.out.println(ColorfulText.getColor("red") + ColorfulText.setBold() + ">>>>> Port is busy, change port or restart server <<<<<" + ColorfulText.reset());
            ColorfulText.setBackground("red");
            System.out.println();
            System.out.println();
            System.exit(1);
        }
    }
}
