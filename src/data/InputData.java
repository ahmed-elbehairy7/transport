package data;

import java.util.Scanner;
import javax.swing.JOptionPane;

import functions.booleanFunction;
import functions.stringFunction;;

public class InputData {

    public static void main() {

    }

    public static String gui(String prompt, booleanFunction valid) {
        return getData(() -> JOptionPane.showInputDialog(prompt) , valid);
    }
    
    public static String cli(Scanner scanner, String prompt, booleanFunction valid) {

        return getData(() -> {
            System.out.println(prompt);
            return scanner.nextLine().trim();
        }, valid);
    }

    public static String cli(Scanner scanner, String prompt) {
        return cli(scanner, prompt, e -> Validations.validString(e));
    }

    private static String getData(stringFunction inputFunction, booleanFunction valid) {
        String data;
        do {
            data = inputFunction.input();
        } while (!valid.check(data));
        return data;
    }

}
