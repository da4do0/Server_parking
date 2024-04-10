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
    private String plateUser = "";
    private CheckerPlate checkerPlate = new CheckerPlate();
    private boolean findPlate;

    public ClientHandler(Socket socket, String ipClient) {
        this.socket = socket;
        this.ipClient = ipClient;
        parking = new Parking();
        ran();
    }

    public void ran() {
        readBuffer();
        createOut();
        askPlate();
        int mess;
        while (true) {
            findPlate = parking.checkPlateParking(plateUser);
            menuOutput(findPlate);
            mess = getIntMess();
            if (mess == -1){
                break;
            }
            mess = translateAscii(mess);
            if (mess <5 && mess >0) {
                if (mess == 4) {
                    System.out.println("exit");
                    out.close();
                    break;
                }
                controllerChoise(mess);
            }


        }
    }

    ////////////////////////////////////////////
    private void readBuffer() {
        try {
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error: read buffer failed");
        }
    }

    private void createOut() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void askPlate() {
        boolean setUser;
        out.println("Please, insert your car's plate");
        out.println("Try: ");
        do {
            String mess = getStringMess();
            setUser = setPlateUser(mess);
            if (!setUser) {
                out.println("\nPlease, check your car's plate and retry");
                out.println("Retry: ");
            }
        } while (!setUser);
        out.println("Caricamento dati in corso...");
    }

    private String getStringMess() {
        String mess = "";
        try {
            mess = read.readLine();
        } catch (IOException e) {
            shutdownClient();
        }

        return mess;
    }

    private void shutdownClient() {
        if (socket != null) {
            try {
                this.socket.close();
                this.read.close();
                this.out.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Client closed");
            }

        }
    }

    private int getIntMess() {
        int mess = 0;
        try {
            mess = read.read();
        } catch (IOException e) {
            shutdownClient();
        }
        return mess;
    }

    private int translateAscii(int mess) {
        switch (mess) {
            case 49 -> {
                return 1;
            }
            case 50 -> {
                return 2;
            }
            case 51 -> {
                return 3;
            }
            case 52 -> {
                return 4;
            }
            default -> {
                return -1;
            }
        }
    }

    private boolean setPlateUser(String mess) {
        int checkPlate;
        try {
            checkPlate = checkerPlate.validatePlate(mess);
            if (checkPlate == 1) {
                plateUser = mess;
                return true;
            }else if (checkPlate == -1){
                shutdownClient();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    private void menuOutput(boolean findPlate) {
        if (findPlate) {
            out.println("");
            out.println("1) Find car");
            out.println("2) Pay place");
            out.println("4) Exit");
        } else {
            out.println("");
            out.println("3) New place");
            out.println("4) Exit");
        }
    }

    private void controllerChoise(int number) {
        String response = "";
        int payPlace = 0;

        switch (number) {
            case 1 -> {
                response = parking.getPlace(plateUser);
                responseGetPlace(response);
            }
            case 2 -> {
                payPlace = parking.calculateRentalCost(plateUser);
                responseGetPay(payPlace);
            }
            case 3 -> {
                response = parking.setNewPlace(plateUser);
                responseNewPlace(response);
            }
        }
    }

    private void responseNewPlace(String response) {
        if (response != null) {
            out.println("");
            out.println("Place found!");
            out.println("Go to place: " + response);
        } else {
            out.println("");
            out.println("Sorry, the parking is full. Try later!");
        }
    }

    private void responseGetPlace(String response) {
        out.println("");
        out.println("Here is your car's place: " + response);
    }

    private void responseGetPay(int response) {
        out.println("");
        out.println("Here is how much you have to pay: " + response);
    }


}