package org.example;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Files;

public class Parking {
    ArrayList<Place> listPlace = new ArrayList<Place>();
    File file = new File("src/main/java/data.json");
    private boolean checkFile;
    public Parking(){
        checkFile = checkfile();
        if (checkFile){

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

    private void fillArrayList(){
        for(int i=0; i<9; i++){
            listPlace.add(new Place());
        }
    }
}
