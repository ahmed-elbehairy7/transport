package behindTheScenes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import functions.stringAndStringToBooleanToString;
import functions.stringToBoolean;

public class Savable {

    public int id;

    public Savable() {
    }
    

    public String toCsv(String text) {
        return this.id + "," + text;
    }
    public String toCsv() {
        return this.id + ",";
    }
    
    public String displayText(String text) {
        return "Id: " + this.id + "\n" + text;
    }

    public String toString(String displayText) {
        return "==============\n" + displayText + "==============\n";
    }

    public void writeInstance(String savedPath) {
        writeToFile(savedPath, toCsv());
    }

    public void fromArray(String[] data) {

    }

    public void editInstance(String keyIndex, stringAndStringToBooleanToString inputFunction, String[] prompts, stringToBoolean[] validators) {
        byte length = (byte) prompts.length;
        String[] old = this.toCsv().split(",");
        String text = this.id + "";
        byte diff = 1;
        for (byte i = 1; old.length > length + i; i++, diff++) {
            text += "," + old[i];
        }
        for (byte i = 0; i < length; i++) {
            if (Integer.parseInt(keyIndex) != i) {
                text += "," + old[i + diff];
                continue;
            }
            text += "," + inputFunction.input(prompts[i] + ": ", validators[i]);
        }

        String[] data = text.split(",");
        fromArray(data);
    }
    
    public static String editables(String[] prompts) {
        String text = "Please choose what to edit:\n\n";
        for (byte i = 0; i < prompts.length; i++) {
            text += "(" + i + ") " + prompts[i] + "\n";
        }
        text += "(S) Save And Exit\n(Q) Quit\n";
        return text;
    }

    
    public static Savable addInstance(String[] prompts, ArrayList<?> instances, stringToBoolean[] validators, String className, String savedPath, String csvHeader, stringAndStringToBooleanToString inputFunction) {
        byte length = (byte) prompts.length;
        String text = generateId(instances) + "";
        for (byte i = 0; i < length; i++) {
            text += "," + inputFunction.input(prompts[i] + ": ", validators[i]);
        }
        String[] data = text.split(",");
        newInstance(data, className);
        saveInstances(instances, savedPath, csvHeader);
        return (Savable) instances.getLast();
    }


    public static int generateId(ArrayList<?> instances) {
        sortInstances(instances);
        if (instances.size() == 0) {
            return 1;
        }
        else {
            return ((Savable) instances.getLast()).id + 1;
        }
    }

    public static void removeInstance(int id, ArrayList<?> instances, String savedPath, String csvHeader) {
        for (short i = 0; i < instances.size(); i++) {
            if (((Savable) instances.get(i)).id == id) {
                instances.remove(i);
                saveInstances(instances, savedPath, csvHeader);
            }
        }

    }

    public static Savable getById(String stringId, ArrayList<?> instances) {
        int id = Integer.parseInt(stringId);
        for (short i = 0; i < instances.size(); i++) {
            Savable savable = (Savable) instances.get(i);
            if (savable.id == id) {
                return savable;
            }
        }
        return new Savable();
    }

    public static void initiateClass(String savedPath, String csvHeader, String className, ArrayList<?> instances) {
        getSaved(instances, savedPath, className, csvHeader);
    }
    
    public static void saveInstances(ArrayList<?> instances, String savedPath, String csvHeader) {
        _writeToFile(savedPath, csvHeader, false);
        sortInstances(instances);
        for (byte i = 0; i < instances.size(); i++) {
            ((Savable) instances.get(i)).writeInstance(savedPath);
        }
    }

    public static void getSaved(ArrayList<?> instances, String savedPath, String className, String csvHeader) {
        instances.clear();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("db\\" + savedPath));
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                newInstance(data, className);
            }
            sortInstances(instances);
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            saveInstances(instances, savedPath, csvHeader);
            getSaved(instances, savedPath, className, csvHeader);
        } catch (IOException e) {
            System.out.println("An error occured while getting stored items");
            e.printStackTrace();
        }
    }

    public static ArrayList<?> sortInstances(ArrayList<?> instances) {
        instances.sort((a, b) -> {
            if (((Savable) a).id < ((Savable) b).id) {
                return -1;
            }
            if (((Savable) a).id < ((Savable) b).id) {
                return 1;
            }
            return 0;

        });
        return instances;
    }

    public static String _listInstances(ArrayList<?> instances) {
        String text = "";
        for (short i = 0; i < instances.size(); i++) {
            text += (instances.get(i).toString()) + "\n";
        }
        return text;
    }

    public static void listInstances(ArrayList<?> instances) {
        System.out.println(_listInstances(instances));
    }

    public static boolean writeToFile(String fileName, String data) {
        return _writeToFile(fileName, data, true);
    }
    
    public static boolean _writeToFile(String fileName, String data, boolean append) {

        try {
            FileWriter fileWriter = new FileWriter("db\\" + fileName, append);
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

    private static void newInstance(String[] data, String className) {
        switch (className) {
            case Trip.className:
                Trip.newInstance(data);
                break;
            case Ticket.className:
                Ticket.newInstance(data);
                break;
            case Vehicle.className:
                Vehicle.newInstance(data);
                break;
            case Passenger.className:
                Passenger.newInstance(data);
                break;
            case Manager.className:
                Manager.newInstance(data);
                break;
            case Driver.className:
                Driver.newInstance(data);
                break;
        
            default:
                break;
        }
    }

}