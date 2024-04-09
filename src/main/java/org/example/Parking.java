package org.example;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.nio.file.Files;
import java.util.Arrays;

public class Parking {
    ArrayList<Place> listPlace = new ArrayList<Place>();
    File file = new File("src/main/java/data.json");
    private boolean checkFile;
    private boolean emptyFile;
    Gson gson = new Gson();
    private String json;

    public Parking(){
        checkFile = checkfile();
        emptyFile = checkDataFile();
        if (checkFile && !emptyFile){
            Place[] parkingArray = gson.fromJson(json, Place[].class);
            ArrayList<Place> listPlace = gson.fromJson(json, ArrayList.class);
        }else{
            fillArrayList();
        }
    }

    private boolean checkfile(){
        if (file.exists()) {
            System.out.println("Il file JSON esiste.");
            return true;
        }
        return false;
    }

    private boolean checkDataFile(){
        if (file.length() == 0) {
            System.out.println("The JSON file is empty.");
            return true;
        }
        return false;
    }
    private void fillArrayList(){
        for(int i=0; i<9; i++){
            listPlace.add(new Place());
        }
    }

    private void readJson(){
        try {
            FileReader reader = new FileReader(file);
            StringBuilder stringBuilder = new StringBuilder();
            int ch;
            createStringBuilder(reader, stringBuilder);
            reader.close();
            json = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            System.out.println("Errore nella lettura del file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createStringBuilder(FileReader reader, StringBuilder stringBuilder){
        int ch;
        while (true) {
            try {
                if (!((ch = reader.read()) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stringBuilder.append((char) ch);
        }
    }

    public boolean checkPlateParking(String plate){
        for (int i = 0; i < listPlace.size(); i++) {
            if(plate.equalsIgnoreCase(listPlace.get(i).getPlate())){
                return true;
            }
        }
        return false;
    }

    public String getPlace(String plate){
        for (int i = 0; i < listPlace.size(); i++) {
            if(plate.equalsIgnoreCase(listPlace.get(i).getPlate())){
                return listPlace.get(i).getNamePlace();
            }
        }
        return null;
    }

    public boolean setNewPlace(String plate){
        for (int i = 0; i < listPlace.size(); i++) {
            if(listPlace.get(i).getPlate().equalsIgnoreCase("")){
                listPlace.get(i).setPlate(plate);
                return true;
            }
        }
        return false;
    }

    public void payPlace(String plate) {
        //Duration duration = Duration.between(startTime, endTime);
        //double minutes = duration.toMinutes();

        // Assuming a minimum charge of 1 hour (60 minutes)
        //double hours = Math.ceil(minutes / 60);
        //double totalFee = hours * 2;

        //return totalFee;
    }
}
