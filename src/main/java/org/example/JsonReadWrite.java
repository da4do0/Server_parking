package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class JsonReadWrite {

    public Place[] readJsonFile(File file){
        Gson gson = new Gson();

        FileReader reader = null;
        reader = openFileJson(file);
        Place[] places = gson.fromJson(reader, Place[].class);
        closeFileJson(reader);
        return places;
    }

    private static FileReader openFileJson(File file) {
        FileReader reader;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return reader;
    }

    private static void closeFileJson(FileReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeJsonFile(String jsonString, File file){
        FileWriter writer = null;
        writer = openFile(file);
        writeFile(jsonString, writer);
        confirmFile(writer);
    }

    private static void confirmFile(FileWriter writer) {
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeFile(String jsonString, FileWriter writer) {
        try {
            writer.write(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static FileWriter openFile(File file) {
        FileWriter writer;
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return writer;
    }
}
