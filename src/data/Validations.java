package data;

import java.util.ArrayList;
import java.util.regex.Pattern;

import behindTheScenes.User;

public class Validations {

    public static boolean validTripType(String text) {
        return text.equals("internal") || text.equals("external");
    }
    public static boolean validCycle(String text) {
        return text.equals("one-way") || text.equals("round-trip");
    }

    public static boolean validEmail(String email) {
        return Pattern.compile(
                "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
                .matcher(email).matches();
    }
    
    public static boolean validPass(String pass) {
        return Pattern.compile(
                "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
                .matcher(pass).matches();
    }

    public static boolean validUsername(String username, ArrayList<User> instances) {
        for (short i = 0; i < instances.size(); i++) {
            if (( instances.get(i)).username.equals(username)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validName(String text) {
        return text.length() > 2;
    }

    public static boolean validString(String text) {
        return text.length() > 4;
    }

    public static boolean validInt(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}