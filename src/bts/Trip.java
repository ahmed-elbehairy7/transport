package bts;
import java.io.*;
import java.util.ArrayList;

import bts.FIO;
import bts.system;


public class Trip {

    public static String TRIPSFILE = "trips.csv";

    private String type;
    private String source;
    private String destination;
    private int stops;
    private int seats;
    private int price;
    private int id;

    public Trip() {
    }

    public Trip(int id, String type, String source, String destination, int stops, int seats, int price) {

        this.id = id;
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.stops = stops;
        this.seats = seats;
        this.price = price;

    }

    public void addTrip() {
        String tripData = this.id + "," + this.source + "," + this.destination + "," + this.stops + "," + this.seats
                + "," + this.price;

        if (FIO.writeToFile(TRIPSFILE, tripData)) {
            System.out.println("Trip was added succesfully");
        }
    }

    public static void removetrip(int id) {

        FileReader fileReader = new FileReader(TRIPSFILE);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> trips = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            trips.add(line);
        }
        
        String tripNumber;
        for (short i = 0; i < trips.size(); i++) {
            System.out.println(trips[i]);
        }

    }   

}


abstract class FakeTrip {

 
    public static void removeTrip(int FakeTripNumber, String fileName) {
        File inputFile = new File(fileName);
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
        boolean FakeTripFound = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("The FakeTrip Number: " + FakeTripNumber + ",")) {
                    FakeTripFound = true;
                } else {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (FakeTripFound) {
            if (!inputFile.delete()) {
                System.out.println("Could not delete the original file.");
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename the temp file.");
            }
        } else {
            System.out.println("FakeTrip number " + FakeTripNumber + " not found!");
            if (!tempFile.delete()) {
                System.out.println("Could not delete the temp file.");
            }
        }
    }
}