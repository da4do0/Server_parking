package org.example;

public class Place {
    static int count = 1;
    private String namePlace;
    private String plate="";
    private int enter, escher;

    public Place(){
        namePlace = "A"+count;
        count++;
    }

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getEnter() {
        return enter;
    }

    public void setEnter(int enter) {
        this.enter = enter;
    }

    public int getEscher() {
        return escher;
    }

    public void setEscher(int escher) {
        this.escher = escher;
    }
}
