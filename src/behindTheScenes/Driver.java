package behindTheScenes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import behindTheScenes.Trip;
import behindTheScenes.User;

public class Driver extends User {

    public static ArrayList<Savable> instances = new ArrayList<>();
    public final static String className = "Driver";
    public final static String savedPath = "drivers.csv";    

    public Driver( String name, String username, String password, String Email) {
        super(generateId(Driver.instances), name, username, password, Email);
        Driver.instances.add(this);

    }

    public Driver(int id, String name, String username, String password, String Email) {
        super(id, name, username, password, Email);
        Driver.instances.add(this);
    }


    public static void listDrivers() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("drivers.csv"));
        bf.readLine();
        String line = bf.readLine();
        while ((line = bf.readLine()) != null) {
            String[] driverData = line.split(",");
            System.out.println("\n\nId: " + driverData[0] + "\nName: " + driverData[1]);
        }
        }
        catch (IOException e) {
            System.out.println("There was an error while lising drivers");
        }
        
    }

    public String toString() {
        return "\n==============\nDriver details:\n\nName:      " + this.name + "\nEmail:     " + this.Email
                + "\nusername:  " + this.username
                + "\n==============\n";
    }
    
    public static void initiateClass() {
        initiateClass(Driver.savedPath, Driver.className, Driver.instances);
    }

    public static void newInstance(String line) {
        String data[] = line.split(",");
        new Driver(Integer.parseInt(data[0]), data[1], data[3], data[4], data[2]);
    }
    
}