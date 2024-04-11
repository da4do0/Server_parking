package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Parking {
    ArrayList<Place> listPlace = new ArrayList<Place>();
    JsonReadWrite jsonFile = new JsonReadWrite();
    File file = new File("data.json");

    private boolean checkFile;
    private boolean emptyFile;
    private boolean read;

    public Parking() {
        checkFile = checkfile();
        emptyFile = checkDataFile();
        if (checkFile && !emptyFile) {
            uploadArrayList();
        } else {
            fillArrayList();
        }
    }

    private void uploadArrayList() {
        Place[] arrayPlace= jsonFile.readJsonFile(file);
        for (Place place : arrayPlace) {
            listPlace.add(place);
        }
    }

    private boolean checkfile() {
        if (file.exists()) {
            return true;
        }
        return false;
    }

    private boolean checkDataFile() {
        if (file.length() == 0) {
            return true;
        }
        return false;
    }

    private void fillArrayList() {
        for (int i = 0; i < 9; i++) {
            listPlace.add(new Place());
        }
    }

    public boolean checkPlateParking(String plate) {
        if (plate != null) {
            for (int i = 0; i < listPlace.size(); i++) {
                if (plate.equalsIgnoreCase(listPlace.get(i).getPlate())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public String getPlace(String plate) {
        for (int i = 0; i < listPlace.size(); i++) {
            if (plate.equalsIgnoreCase(listPlace.get(i).getPlate())) {
                return listPlace.get(i).getNamePlace();
            }
        }
        return null;
    }

    public int getIndexPlace(String plate) {
        for (int i = 0; i < listPlace.size(); i++) {
            if (plate.equalsIgnoreCase(listPlace.get(i).getPlate())) {
                return i;
            }
        }
        return -1;
    }

    public String setNewPlace(String plate) {
        for (int i = 0; i < listPlace.size(); i++) {
            if (listPlace.get(i).getPlate().equalsIgnoreCase("")) {
                listPlace.get(i).setPlate(plate);
                listPlace.get(i).setEnter(getDate());
                jsonFile.writeJsonFile(createGsonObject(), file);
                read= true;
                return listPlace.get(i).getNamePlace();
            }
        }
        return null;
    }

    private String createGsonObject() {
        Gson gson = new Gson();
        String ciao = gson.toJson(listPlace);
        return ciao;
    }

    private long getDate() {
        Date now = new Date();
        return now.getTime();
    }

    public long calculateHoursDifference(int indexPlace) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(new Date(listPlace.get(indexPlace).getEnter()));
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(new Date(getDate()));

        long differenceInMilliSeconds = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
        return differenceInMilliSeconds / (60 * 60 * 1000);
    }

    public int calculateRentalCost(String plateUser) {
        int indexPlace = getIndexPlace(plateUser);
        long hoursDifference = calculateHoursDifference(indexPlace);
        removePlateUser(indexPlace);
        if ((int) hoursDifference <= 0) {
            return 2;
        } else {
            return (int) (hoursDifference * 2);
        }
    }

    public void removePlateUser(int indexPlace) {
        listPlace.get(indexPlace).setEnter(0);
        listPlace.get(indexPlace).setPlate("");
        jsonFile.writeJsonFile(createGsonObject(), file);
    }
}
