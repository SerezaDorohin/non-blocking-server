package main.java.com.client;

import main.java.com.methods.ColorfulText;

import java.io.IOException;

public class StartClient {
    public static void main(String[] args) throws IOException {
        try {
            Client.client();
        } catch (java.net.ConnectException e) {
            System.out.println();
            ColorfulText.setBackground("red");
            System.out.println();
            System.out.println();
            System.out.println(ColorfulText.getColor("red") + ColorfulText.setBold() + ">>>>> Port is unavailable, change port and try again. <<<<<" + ColorfulText.reset());
            ColorfulText.setBackground("red");
            System.out.println();
            System.out.println();
            System.exit(1);
        }
    }
}
