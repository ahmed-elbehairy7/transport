package bts;
import java.io.*;
public class Manager extends Employee {
    public Manager() {
        super("manager", "managers.csv");
    }
    public void assignDriverToTrip(String DriverEmail, int tripNumber) {
        String tripDetails = searchTrip(tripNumber);
        String driverName = getDriverName(DriverEmail); // Assuming the email is unique and known

        if (tripDetails != null && driverName != null) {
            writeAssignmentToFile(tripDetails, driverName);
        } else {
            System.out.println("Trip details or driver name not found.");
        }
    }
    private String searchTrip(int tripNumber) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Trips.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("The Trip Number: " + tripNumber)) {
                    return line;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the trip file.");
            e.printStackTrace();
        }
        return null;
    }

    private String getDriverName(String email) { //By Email
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Driver.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] driverData = line.split(",");
                if (driverData.length > 1 && driverData[1].equals(email)) {
                    return driverData[0];
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the driver file.");
            e.printStackTrace();
        }
        return null;
    }
    private void writeAssignmentToFile(String tripDetails, String driverName) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("AssignedTripsWithDrivers.txt", true)))) {
            out.println(tripDetails + ", " +"The Driver Name: "+ driverName);
            System.out.println("Done !");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}


