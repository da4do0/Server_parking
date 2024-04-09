package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckerPlate {
    private String regex = "^(?:[A-Z]{2}\\s?)?\\d{1,4}[A-Z]{1,2}$";

    public boolean validatePlate(String plate){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(plate);
        return matcher.matches();
    }
}
