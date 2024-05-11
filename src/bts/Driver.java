package bts;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Driver extends Employee {
    public Driver() {
        super("driver", "drivers.csv");
    }
    public void Info(String userName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Driver.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length > 2 && userData[2].trim().equals(userName.trim())) {
                    System.out.println("Driver Name: " + userData[0].trim());
                    System.out.println("Driver Email: " + userData[1].trim());
                    System.out.println("Driver Username: " + userData[2].trim());
                    return;
                }
            }
            System.out.println("User not found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
    public void viewAssignedTrips(String driverName) {
        String fileName = "AssignedTripsWithDrivers.txt";
        boolean isDriverFound = false;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String prefix = "The Driver Name: ";
                int startIndex = line.indexOf(prefix);
                if (startIndex != -1) {
                    String nameInFile = line.substring(startIndex + prefix.length()).trim();
                    if (nameInFile.equals(driverName)) {
                        System.out.println(line);
                        isDriverFound = true;
                        break;
                    }
                }
            }
            if (!isDriverFound) {
                System.out.println("The Driver Not Found");
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}