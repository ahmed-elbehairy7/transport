package bts;
import java.io.*;
import java.util.ArrayList;

import bts.FIO;
import bts.system;


public class Trip {

    public static String CSVOPEN = "id,type,source,destination,stops,seats,price";
    public static String TRIPSFILE = "trips.csv";
    public static ArrayList<Trip> trips = new ArrayList<Trip>();

    private String type;
    private String source;
    private String destination;
    private int stops;
    private int seats;
    private int price;
    private int id;
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

    public void addTrip(boolean saving) {
        String tripData = toCsv();
        FIO.writeToFile(TRIPSFILE, tripData);

        if (!saving) {
            System.out.println("Trip was added succesfully");
        }
    }
    
    public String toCsv() {
        return this.id + "," + this.source + "," + this.destination + "," + this.stops + "," + this.seats + "," + this.price + "," + this.driver;
    }
    
    public String toString() {
        return "==============\n" + "ID: " + this.id + "\nSource: " + this.source + "\nDestination: " + this.destination + "\nStops: "
                + this.stops + "\nSeats: " + this.seats + "\n==============\n";
    }

    public static void removeTrip(int id) {
        for (short i = 0; i < trips.size(); i++) {
            if (trips.get(i).id == id) {
                trips.remove(i);
                Trip.saveTrips();
            }
        }

    }

    public static void listTrips() {

        System.out.println("This is list of the available trips:\n\n");

        for (short i = 0; i < trips.size(); i++) {
            System.out.println(trips.get(i).toString() );
        }
    }

    public static void saveTrips() {
        FIO._writeToFile(TRIPSFILE, CSVOPEN, false);

        for (short i = 0; i < trips.size(); i++) {
            trips.get(i).addTrip(true);
        }
    }

}