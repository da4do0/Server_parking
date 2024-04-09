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
    private String plateUser="";
    private CheckerPlate checkerPlate = new CheckerPlate();
    public ClientHandler(Socket socket, String ipClient) {
        this.socket = socket;
        this.ipClient = ipClient;
        parking = new Parking();
        System.out.println("new client");
        ran();
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
            if(mess != null || !mess.equalsIgnoreCase("")) {
                menuOutput(mess);
            } else if (mess.equalsIgnoreCase("")) {
                System.out.println("Client (" + ipClient + ") has disconnected!");
                break;
            }
        }
    }

    private void menuOutput(String mess){
        boolean checkPlate;
        if (plateUser.equalsIgnoreCase("")){
            setPlateUser(mess);
        }
        if(parking.checkPlateParking(plateUser)){
            out.println("1) Find car\n" +
                        "2) Pay place\n" +
                        "3) exit");
        }else{
            out.println("1) New place\n" +
                        "2) exit\n");
        }
    }

    private void setPlateUser(String mess) {
        boolean checkPlate;
        checkPlate = checkerPlate.validatePlate(mess);
        if (checkPlate){
            plateUser = mess;
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