package main.java.com.client;

import main.java.com.methods.ColorfulText;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Scanner;

public class Client {
    private static InetSocketAddress crunchifyAddr;
    private static SocketChannel crunchifyClient;
    private static Scanner scanner;
    private static int port;
    private static String input;
    private static ColorfulText text;
    private static String hello = "\"/close\" - disconnect from server\n``````````````````````````````````\n";

    public static void client() throws IOException {
        text = new ColorfulText();
        port = 2226;

        text.constructor("green", hello);

        crunchifyAddr = new InetSocketAddress("localhost", port);
        crunchifyClient = SocketChannel.open(crunchifyAddr);
        scanner = new Scanner(System.in);

        while (true) {
            System.out.print(text.getColor("blue") + "Enter text: " + text.setBold());
            input = scanner.nextLine();

            byte[] message = input.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            crunchifyClient.write(buffer);

            if(input.equals("/close")) {
                crunchifyClient.close();
                System.out.print(text.getColor("red") + text.setBold() + "Disconnected.");
                System.out.println();
                System.exit(0);
            }

            readFromChannel(crunchifyClient);
            System.out.println();

            buffer.clear();
        }
    }

    private static void readFromChannel(SocketChannel socketChannel ) {
        Charset charset = Charset.forName("ISO-8859-1");

        ByteBuffer buf = ByteBuffer.allocate(48);
        CharsetDecoder decoder = charset.newDecoder();


        try {
            int bytesRead = socketChannel.read(buf);
            if (bytesRead == -1) {
                socketChannel.close();
            }
            buf.flip();
            String request = decoder.decode(buf).toString();

            client(request);

            buf.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void client(String info) {
        ColorfulText.client(info);
    }
}
