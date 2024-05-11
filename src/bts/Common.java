package bts;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Common {

    public static boolean writeToFile(String fileName, String data) {
        return _writeToFile(fileName, data, true);
    }
    
    public static boolean _writeToFile(String fileName, String data, boolean append) {

        try {
            FileWriter fileWriter = new FileWriter(fileName, append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file. { " + fileName + " }");
            e.printStackTrace();
            return false;
        }

    }

    public static String getData(Scanner scanner, String prompt) {
        String data;
        do {
            System.out.print(prompt);
            data = scanner.nextLine();
        } while (data.length() < 4);
        return data;
    }
}
