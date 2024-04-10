package org.example;

public class Place {
    static int count = 1;
    private String namePlace;
    private String plate="";
    private long enter=0;

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

    public long getEnter() {
        return enter;
    }

    public void setEnter(long enter) {
        this.enter = enter;
    }
}
