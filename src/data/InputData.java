package data;

import java.util.Scanner;
import javax.swing.JOptionPane;

import functions.stringToBoolean;
import functions.voidToString;;

public class InputData {

    public static String gui(String prompt, stringToBoolean valid) {
        return getData(() -> JOptionPane.showInputDialog(prompt) , valid);
    }
    
    public static String cli(Scanner scanner, String prompt, stringToBoolean valid) {

        return getData(() -> {
            System.out.print(prompt);
            return scanner.nextLine().trim();
        }, valid);
    }

    public static String cli(Scanner scanner, String prompt) {
        return cli(scanner, prompt, e -> Validations.validString(e));
    }

    private static String getData(voidToString inputFunction, stringToBoolean valid) {
        String data;
        do {
            data = inputFunction.input();
        } while (!valid.check(data));
        return data;
    }

}
