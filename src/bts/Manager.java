package bts;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import bts.Driver;
import bts.Trip;
import bts.Vehicle;

public class Manager extends User {

    public static ArrayList<Savable> instances = new ArrayList<>();
    public final static String className = "Manager";
    public final static String savedPath = "managers.csv";    

    public Manager(int id, String name, String username, String password, String Email) {
        super(id, name, username, password, Email);
        Manager.instances.add(this);
    }
    public Manager() {
        super(Manager.instances, Manager.savedPath);
        Manager.instances.add(this);
    }

    public void startFlow() {

        /*
        If you log in as a manager you are able to review all trips in the system,you are able to add / remove trips and assign drivers the trips in the system 
        */

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println(
                    "Please choose one of the following:\n\n(L) List all trips\n(A) Add a trip\n(R) Remove a trip\n(E) Edit a trip\n(D) Assign a driver\n(V) Add a vehicle\n(M) Add an Employee (driver)\n(G) Generate report\n(Q) Quit\n\n");

            switch (scanner.nextLine().toUpperCase()) {
                case "L":
                    Trip.listInstances(Trip.instances);
                    break;
                case "A":
                    addTrip(scanner);
                    break;
                case "R":
                    removeTrip(scanner);
                    break;
                case "E":
                    editTrip(scanner, false);
                    break;
                case "D":
                    editTrip(scanner, true);
                    break;
                case "V":
                    addVehicle(scanner);
                    break;
                case "M":
                    addEmployee(scanner);
                    break;
                case "G":
                    generateReport(scanner);
                    break;
                case "Q":
                    return;
                default:
                    break;
            }

        }

    }

    public void generateReport(Scanner scanner) {
        System.out.println("Managers in company");
    }

    public void addEmployee(Scanner scanner) {
        String Name = getData(scanner, "Name: ");
        String Email = getData(scanner, "Email: ");
        String Username = getData(scanner, "Username: ");
        new Driver(Name, Username, "initial", Email);
        saveInstances(Driver.instances, Driver.savedPath, Driver.csvHeader);
    }

    public void addVehicle(Scanner scanner) {
        
        String Type = getData(scanner, "Type: ");
        int Capacity = Integer.parseInt(_getData(scanner, "Capacity: ", 1));
        String LicensePlate = getData(scanner, "License Plate: ");

        Vehicle vehicle = new Vehicle(Type, Capacity, LicensePlate);
        Vehicle.saveInstances(Vehicle.instances, Vehicle.savedPath, Vehicle.csvHeader);
    }

    public void addTrip(Scanner scanner) {

        String source = getData(scanner, "Source: ");
        String Destination = getData(scanner, "Destination: ");
        String Type = getData(scanner, "Type: ");
        String Stops = _getData(scanner, "Stops: ", 1);
        String Seats = _getData(scanner, "Seats: ", 1);
        String Price = _getData(scanner, "Price: ", 1);
        String DriverId = _getData(scanner, "DriverId: ", 1);
        String cycle = getData(scanner, "Cycle: ");


        Trip trip = new Trip(Type, source, Destination, Integer.parseInt(Stops), Integer.parseInt(Seats), Integer.parseInt(Price), Integer.parseInt(DriverId), cycle);

        trip.writeInstance(Trip.savedPath);

    }

    public void removeTrip(Scanner scanner) {
        System.out.println("Please type the id of the trip you want to remove");
        String id = scanner.nextLine();
        if (id.isEmpty()) {
            return;
        }
        Trip.removeInstance(Integer.parseInt(id), Trip.instances, Trip.savedPath, Trip.csvHeader);
    }
    
    public void editTrip(Scanner scanner, boolean driver) {
        System.out.println("Please type the id of the trip you want to edit: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (short i = 0; i < Trip.instances.size(); i++) {
            Trip trip = (Trip) Trip.instances.get(i);
            if (trip.id != id) {
                continue;
            }
            if (driver) {
                trip.assignDriver();
                return;
            }
            trip.editTrip();
            return;
        }
    }
    
    public static void initiateClass() {
        initiateClass(Manager.savedPath, Manager.className, Manager.instances);
    }

    public static void newInstance(String line) {
        String data[] = line.split(",");
        new Manager(Integer.parseInt(data[0]), data[1], data[3], data[4], data[2]);
    }
    

}