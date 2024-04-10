package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonReadWrite {

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
