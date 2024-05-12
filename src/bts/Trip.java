package bts;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import bts.Common;

public class Trip {

    public static String CSVOPEN = "id,type,source,destination,stops,seats,price,driver";
    public static String TRIPSFILE = "trips.csv";
    public static ArrayList<Trip> trips = new ArrayList<Trip>();

    public String type;
    public String source;
    public String destination;
    public int stops;
    public int seats;
    public int price;
    public int id;
    public String driver;

    public Trip(String type, String source, String destination, int stops, int seats, int price, String driver) {

        if (trips.size() == 0) {
            this.id = 1;
        }
        else {
            this.id = trips.getLast().id + 1;
        }
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.stops = stops;
        this.seats = seats;
        this.price = price;
        this.driver = driver;

        trips.add(this);

    }

    public Trip(int id, String type, String source, String destination, int stops, int seats, int price, String driver) {

        this.id = id;
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.stops = stops;
        this.seats = seats;
        this.price = price;
        this.driver = driver;

        trips.add(this);

    }

    public static void getTrips() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(TRIPSFILE));
        String line;
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            String[] tripData = line.split(",");

            new Trip(Integer.parseInt(tripData[0]), tripData[1], tripData[2], tripData[3], Integer.parseInt(tripData[4]), Integer.parseInt(tripData[5]), Integer.parseInt(tripData[6]), tripData[7]);
        }
    } catch (FileNotFoundException e) {
        saveTrips();
        getTrips();
    } catch (IOException e) {
        System.out.println("An error occured while getting stored trips");
        e.printStackTrace();
    }
        
    }

    public void addTrip() {
        Common.writeToFile(TRIPSFILE, toCsv());
    }
    
    public String toCsv() {
        return this.id + "," + this.source + "," + this.destination + "," + this.stops + "," + this.seats + ","
                + this.price + "," + this.driver;
    }
    
    public String info() {
        return "ID: " + this.id + "\nSource: " + this.source + "\nDestination: " + this.destination + "\nStops: "
                + this.stops;
    }

    public String allInfo() {
        return "ID: " + this.id + "\nSource: " + this.source + "\nDestination: " + this.destination + "\nStops: "
                + this.stops + "\nSeats: " + this.seats + "\nDriver: " + this.driver + "\nType: " + this.type +"\nPrice: " + this.price;
    }
    
    public String toString(String info) {
        return "==============\n" + info + "\n==============\n";
    }

    public static void removeTrip(int id) {
        for (short i = 0; i < trips.size(); i++) {
            if (trips.get(i).id == id) {
                trips.remove(i);
                Trip.saveTrips();
                System.out.println("\n\nTrip with id " + id + " successfully deleted\n\n");
            }
        }

    }

    public void editTrip() {
        Scanner scanner = new Scanner(System.in);
        String prompt = "\nNew Value: ";
        while (true) {
            System.out.println(
                    "Please choose what to edit:\n\n(0) Source\n(1) Destination\n(2) Type\n(3) Stops\n(4) Seats\n(5) Price\n(6) Driver\n(S) Save and exit\n(Q) Quit without saving\n\n");

            switch (scanner.nextLine()) {
                case "0":
                    this.source = Common.getData(scanner, prompt);
                    break;
                case "1":
                    this.source = Common.getData(scanner, prompt);
                    break;
                case "2":
                    this.source = Common.getData(scanner, prompt);
                    break;
                case "3":
                    this.source = Common.getData(scanner, prompt);
                    break;
                case "4":
                    this.source = Common.getData(scanner, prompt);
                    break;
                case "5":
                    this.source = Common.getData(scanner, prompt);
                    break;
                case "6":
                    this.source = Common.getData(scanner, prompt);
                    break;
                case "S":
                    saveTrips();
                    return;
                case "Q":
                    return;
                default:
                    break;
            }
        }
    }

    public static void listTrips() {

        System.out.println("This is list of the available trips:\n\n");

        for (short i = 0; i < trips.size(); i++) {
            Trip trip = trips.get(i);
            System.out.println(trip.toString(trip.info()) );
        }
    }

    public static void saveTrips() {
        Common._writeToFile(TRIPSFILE, CSVOPEN, false);

        for (short i = 0; i < trips.size(); i++) {
            trips.get(i).addTrip();
        }
    }

}