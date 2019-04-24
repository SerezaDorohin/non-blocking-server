package main.java.com.server;

import main.java.com.methods.ColorfulText;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    private static Selector selector;
    private static SelectionKey selectionKey;
    private static SelectionKey myKey;

    private static ServerSocketChannel crunchifySocket;
    private static InetSocketAddress crunchifyAddr;
    private static SocketChannel crunchifyClient;
    private static ByteBuffer crunchifyBuffer;
    private static int ops;
    private static int port;
    private static String result;

    private static ColorfulText text;

    private static Set<SelectionKey> crunchifyKeys;
    private static Iterator<SelectionKey> crunchifyIterator;

    private static String hello = "Non-blocking server v0.1-BETA\nAuthor: Sergey Dorohin\n``````````````````````````````\n";

    public static void server() throws IOException {
        port = 2226;

        text = new ColorfulText();
        text.constructor("green", hello);

        terminal("starting server on port " + text.setBold() + port + text.setPlain());

        selector = Selector.open();

        crunchifySocket = ServerSocketChannel.open();
        crunchifyAddr = new InetSocketAddress("localhost", port);

        crunchifySocket.bind(crunchifyAddr);
        crunchifySocket.configureBlocking(false);

        ops = crunchifySocket.validOps();

        selectionKey = crunchifySocket.register(selector, ops, null);

        while (true) {
            selector.select();

            crunchifyKeys = selector.selectedKeys();
            crunchifyIterator = crunchifyKeys.iterator();

            while (crunchifyIterator.hasNext()) {
                myKey = crunchifyIterator.next();

                if (myKey.isAcceptable()) {
                    crunchifyClient = crunchifySocket.accept();
                    crunchifyClient.configureBlocking(false);
                    crunchifyClient.register(selector, SelectionKey.OP_READ);

                    terminal("Connection accepted from " +  text.setBold() + text.getColor("yellow") +
                            crunchifyClient.getLocalAddress() + text.setPlain() + text.getColor("red"));
                } else if (myKey.isReadable()) {

                    crunchifyClient = (SocketChannel) myKey.channel();
                    crunchifyBuffer = ByteBuffer.allocate(256);
                    crunchifyClient.read(crunchifyBuffer);
                    result = new String(crunchifyBuffer.array()).trim();

                    if (result.equals("/close")) {
                        terminal(text.reset() + text.getColor("yellow") + text.setBold() + crunchifyClient.getLocalAddress() +
                                text.setPlain() + text.getColor("red") + " was disconnected");
                        crunchifyClient.close();
                    } else {
                        writeToChannel(crunchifyClient, result.toLowerCase());

                        terminal("Message received from " + text.reset() + text.setBold() + text.getColor("yellow") + crunchifyClient.getLocalAddress() +
                                text.getColor("red") + text.setPlain() + ": \"" + text.setBold() + result + text.setPlain() + text.getColor("red") + "\"");
                    }
                }

                crunchifyIterator.remove();
            }
        }
    }

    private static void terminal(String info) {
        ColorfulText.terminal(info);
    }

    private static void writeToChannel(SocketChannel socketChannel, String data)
    {
        String newData = data;

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();
        while(buf.hasRemaining()) {
            try {
                socketChannel.write(buf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
