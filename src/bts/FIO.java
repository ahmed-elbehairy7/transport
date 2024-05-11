package bts;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FIO {
    
    public static boolean writeToFile(String fileName, String data) {

        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
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
}
