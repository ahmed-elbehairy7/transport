package bts;

import java.io.*;
import java.util.Scanner;

import bts.Trip;

public class Manager extends User {
    public Manager() {
        super("manager");
    }

    public void startFlow() {

        /*
        If you log in as a manager you are able to review all trips in the system,you are able to add / remove trips and assign drivers the trips in the system 
        */

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println(
                    "Please choose one of the following:\n\n(L) List all trips\n(A) Add a trip\n(R) Remove a trip\n(E) Edit a trip\n(Q) Quit\n\n");

            switch (scanner.nextLine().toUpperCase()) {
                case "L":
                    Trip.listTrips();
                    break;

                case "A":
                    addTrip(scanner);
                    break;
                case "R":
                    removeTrip(scanner);
                    break;
                case "E":
                    editTrip(scanner);
                case "Q":
                    return;
                default:
                    break;
            }

        }

    }

    public void addTrip(Scanner scanner) {

        String source = Common.getData(scanner, "Source: ");
        String Destination = Common.getData(scanner, "Destination: ");
        String Type = Common.getData(scanner, "Type: ");
        String Stops = Common._getData(scanner, "Stops: ", 1);
        String Seats = Common._getData(scanner, "Seats: ", 1);
        String Price = Common._getData(scanner, "Price: ", 1);
        String Driver = Common.getData(scanner, "Driver: ");

        Trip trip = new Trip(Type, source, Destination, Integer.parseInt(Stops),
                Integer.parseInt(Seats), Integer.parseInt(Price), Driver);

        trip.addTrip();

    }

    public void removeTrip(Scanner scanner) {
        System.out.println("Please type the id of the trip you want to remove");
        String id = scanner.nextLine();
        if (id.isEmpty()) {
            return;
        }
        Trip.removeTrip(Integer.parseInt(id));
    }
    
    public void editTrip(Scanner scanner) {
        System.out.println("Please type the id of the trip you want to edit: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (short i = 0; i < Trip.trips.size(); i++) {
            Trip trip = Trip.trips.get(i);
            if (trip.id == id) {
                trip.editTrip();
                return;
            }
        }
    }

}

// public class Manager extends Employee {
//     public Manager() {
//         super("manager", "managers.csv");
//     }
//     public void assignDriverToTrip(String DriverEmail, int tripNumber) {
//         String tripDetails = searchTrip(tripNumber);
//         String driverName = getDriverName(DriverEmail); // Assuming the email is unique and known

//         if (tripDetails != null && driverName != null) {
//             writeAssignmentToFile(tripDetails, driverName);
//         } else {
//             System.out.println("Trip details or driver name not found.");
//         }
//     }
//     private String searchTrip(int tripNumber) {
//         try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Trips.txt"))) {
//             String line;
//             while ((line = bufferedReader.readLine()) != null) {
//                 if (line.contains("The Trip Number: " + tripNumber)) {
//                     return line;
//                 }
//             }
//         } catch (IOException e) {
//             System.out.println("An error occurred while reading the trip file.");
//             e.printStackTrace();
//         }
//         return null;
//     }

//     private String getDriverName(String email) { //By Email
//         try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Driver.txt"))) {
//             String line;
//             while ((line = bufferedReader.readLine()) != null) {
//                 String[] driverData = line.split(",");
//                 if (driverData.length > 1 && driverData[1].equals(email)) {
//                     return driverData[0];
//                 }
//             }
//         } catch (IOException e) {
//             System.out.println("An error occurred while reading the driver file.");
//             e.printStackTrace();
//         }
//         return null;
//     }
//     private void writeAssignmentToFile(String tripDetails, String driverName) {
//         try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("AssignedTripsWithDrivers.txt", true)))) {
//             out.println(tripDetails + ", " +"The Driver Name: "+ driverName);
//             System.out.println("Done !");
//         } catch (IOException e) {
//             System.out.println("An error occurred while writing to the file.");
//             e.printStackTrace();
//         }
//     }
// }
