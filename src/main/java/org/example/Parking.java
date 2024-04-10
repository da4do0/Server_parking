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
import java.util.Calendar;
import java.util.Date;

public class Parking {
    ArrayList<Place> listPlace = new ArrayList<Place>();
    File file = new File("src/main/java/data.json");
    private boolean checkFile;
    private boolean emptyFile;
    Gson gson = new Gson();
    private String json;

    public Parking() {
        checkFile = checkfile();
        emptyFile = checkDataFile();
        if (checkFile && !emptyFile) {
            Place[] parkingArray = gson.fromJson(json, Place[].class);
            ArrayList<Place> listPlace = gson.fromJson(json, ArrayList.class);
        } else {
            fillArrayList();
        }
    }

    private boolean checkfile() {
        if (file.exists()) {
            System.out.println("Il file JSON esiste.");
            return true;
        }
        return false;
    }

    private boolean checkDataFile() {
        if (file.length() == 0) {
            System.out.println("The JSON file is empty.");
            return true;
        }
        return false;
    }

    private void fillArrayList() {
        for (int i = 0; i < 9; i++) {
            listPlace.add(new Place());
        }
    }

    private void readJson() {
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

    private static void createStringBuilder(FileReader reader, StringBuilder stringBuilder) {
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
                return listPlace.get(i).getNamePlace();
            }
        }
        return null;
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
    }
}
