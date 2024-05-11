package bts;
import java.io.*;
import java.util.ArrayList;

import bts.Common;

public class Trip {

    public static String CSVOPEN = "id,type,source,destination,stops,seats,price";
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
        String tripData = toCsv();
        Common.writeToFile(TRIPSFILE, tripData);
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
            }
        }

    }

    public void editTrip() {
        while (true) {
            return;
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