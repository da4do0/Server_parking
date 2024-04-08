package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.System.out;

public class ClientHandler {
    private Socket socket;
    private BufferedReader read;
    private PrintWriter out;
    private String ipClient;
    private Parking parking;
    public ClientHandler(Socket socket, String ipClient) {
        this.socket = socket;
        this.ipClient = ipClient;
        ran();
        parking = new Parking();
    }

    public void ran() {
        readBuffer();
        createOut();
        loopPrint();
    }

    private void readBuffer() {
        try {
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createOut() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loopPrint() {
        String mess;
        while (true) {
            mess = getMess();
            if (mess != null || !mess.equalsIgnoreCase("")) {
                out.println(mess);
                System.out.println(mess);
            }
            if(mess.equalsIgnoreCase("")){
                System.out.println("Client ("+ ipClient +") has disconnected!");
                break;
            }
        }
    }

    private String getMess() {
        String mess = "";
        try {
            mess = read.readLine();
        } catch (IOException e) {
            out.close();
        }

        return mess;
    }
}