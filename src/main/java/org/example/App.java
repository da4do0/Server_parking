package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.Date;

public class App {
    static int portNumber = 5001;
    static String ipClient;

    /*-------------START DECLARATION OBJ------------*/
    private static Socket clientSocket;
    private static ServerSocket serverSocket;
    /*-------------END DECLARATION OBJ------------*/
    public static void main( String[] args ) {
        System.out.println("Server started!");
        do {
            getServerSocket();
        }while(serverSocket == null);
        System.out.println("Use port: "+portNumber);

        Date now = new Date();
        System.out.println(now);

        while (true) {
            getSocket();
            getIpClient();
            createClient();
        }
    }
    private static void getIpClient() {
        InetSocketAddress remoteAddress = (InetSocketAddress) clientSocket.getRemoteSocketAddress();
        ipClient = remoteAddress.getAddress().getHostAddress();
        System.out.println("Client connected with this ip:" + ipClient);
    }

    private static void getSocket() {
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createClient() {
        Thread thread = new Thread(() -> {
            new ClientHandler(clientSocket, ipClient);
        });
        thread.start();
    }

    private static void getServerSocket() {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            portNumber ++;
        }
    }
}

