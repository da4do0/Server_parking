package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private BufferedReader read;
    private PrintWriter out;
    private String ipClient;
    private Parking parking;
    private String plateUser="";
    private CheckerPlate checkerPlate = new CheckerPlate();
    private boolean findPlate;

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
        askPlate();
        findPlate = parking.checkPlateParking(plateUser);
        int mess;
        while(true) {
            menuOutput(findPlate);
            mess = getIntMess();
            if(mess == 4){
                break;
            }
            controllerChoise(mess);
        }
    }
    ////////////////////////////////////////////
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

    private void askPlate(){
        boolean setUser;
        out.println("Please, insert your car's plate");
        out.println("Try: ");
        do{
            String mess = getStringMess();
            setUser = setPlateUser(mess);
            if (!setUser){
                out.println("\nPlease, check your car's plate and retry");
                out.println("Retry: ");
            }
        }while(!setUser);
        out.println("Caricamento dati in corso...");
    }

    private String getStringMess() {
        String mess = "";
        try {
            mess = read.readLine();
        } catch (IOException e) {
            out.close();
        }

        return mess;
    }

    private int getIntMess(){
        int mess=0;
        try {
            mess = read.read();
        } catch (IOException e) {
            out.close();
        }
        return mess;
    }

    private boolean setPlateUser(String mess) {
        boolean checkPlate;
        checkPlate = checkerPlate.validatePlate(mess);
        if (checkPlate){
            plateUser = mess;
            return true;
        }
        return false;
    }

    private void menuOutput(boolean findPlate){
        if(findPlate){
            out.println("1) Find car\n" +
                    "2) Pay place\n" +
                    "4) Exit");
        }else{
            out.println("3) New place");
            out.println("4) Exit");
        }
    }

    private void controllerChoise(int number){
        switch(number){
            case 1 ->{
                parking.getPlace(plateUser);
            }
            case 2 ->{
                parking.payPlace(plateUser);
            }
            case 3 ->{
                parking.setNewPlace(plateUser);
            }
        }
    }

    ///////////////////////////////////////////////

    /*private void loopPrint() {
        String mess;
        while (true) {
            mess = getMess();
            if(mess != null || !mess.equalsIgnoreCase("")) {
            } else if (mess.equalsIgnoreCase("")) {
                System.out.println("Client (" + ipClient + ") has disconnected!");
                break;
            }
        }
    }*/

}