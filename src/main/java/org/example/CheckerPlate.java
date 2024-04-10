package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckerPlate {
    private String regex = "^(?:[A-ZÄÖÜß]{2}[A-ZÄÖÜß]{3}[0-9]{3}|[A-ZÄÖÜß]{2}[0-9]{5}|[0-9]{2}[A-ZÄÖÜß]{4})$";

    public int validatePlate(String plate){
        String platenew="";
        if(plate != null){
            platenew = plate.toUpperCase();
            platenew = platenew.replace(" ", "");
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(platenew);
            if (matcher.matches()){
                return 1;
            }
            return 0;
        }
        return -1;
    }
}
