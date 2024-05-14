package behindTheScenes;

import java.util.ArrayList;

import data.Validations;
import functions.stringAndStringToBooleanToString;
import functions.stringToBoolean;

public class Trip extends Savable {

    public static ArrayList<Savable> instances = new ArrayList<>();
    public final static String csvHeader = "id,type,source,destination,stops,seats,price,driverId,cycle";
    public final static String savedPath = "trips.csv";
    public final static String className = "Trip";
    public final static String[] prompts = {"Type", "Source", "Destination", "Stops", "Seats", "Price", "DriverId", "Cycle"};
    public final static stringToBoolean[] validators = {e -> Validations.validTripType(e), e -> Validations.validString(e), e -> Validations.validString(e), e -> Validations.validInt(e), e -> Validations.validInt(e), e -> Validations.validInt(e), e -> Validations.validInt(e), e -> Validations.validCycle(e)};

    public String type;
    public String source;
    public String destination;
    public String cycle;
    public int stops;
    public int seats;
    public int price;
    public int driverId;
    
    public Trip() {
    }

    public Trip(int id, String type, String source, String destination, int stops, int seats, int price, int driverId,
            String cycle) {
        super();
        this.id = id;
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.stops = stops;
        this.seats = seats;
        this.price = price;
        this.cycle = cycle;
        this.driverId = driverId;
        Trip.instances.add(this);
    }

    public void fromArray(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.type = data[1];
        this.source = data[2];
        this.destination = data[3];
        this.stops = Integer.parseInt(data[4]);
        this.seats =Integer.parseInt(data[5]);
        this.price = Integer.parseInt(data[6]);
        this.driverId = Integer.parseInt(data[7]);
        this.cycle = data[8];
    }
    
    public String allDetails() {
        return displayText() + "Type: " + this.type + "\nCycle: " +this.cycle + "\nDriver Id: " + this.driverId + "\nPrice: " + this.price + "\n";
    }
    
    public String toCsv() {
        return toCsv(this.type + "," + this.source + "," + this.destination + "," + this.stops + ","
                + this.seats + "," + this.price + "," + this.driverId + "," + this.cycle);
    }

    public String displayText() {
        return displayText("Source: " + this.source + "\nDestination: " + this.destination + "\nStops: "
                + this.stops + "\nSeats: " + this.seats + "\n");
    }
    
    public String toString() {
        return toString(displayText());
    }
    
    public void writeInstance() {
        writeInstance(savedPath);
    }

    public void editInstance(String keyIndex, stringAndStringToBooleanToString inputFunction) {
        editInstance(keyIndex, prompts, instances, validators, className, savedPath, csvHeader, inputFunction);
    }

    public static String editables() {
        return editables(Trip.prompts);
    }

    
    public static Savable addInstance(stringAndStringToBooleanToString inputFunction) {
        return addInstance(prompts, instances, validators, className, savedPath, csvHeader, inputFunction);
    }


    public static int generateId() {
        return generateId(instances);
    }

    public static void removeInstance(int id) {
        removeInstance(id, instances, savedPath, csvHeader);
    }

    public static Savable getById(String id) {
        return getById(id, instances);
    }

    public static void initiateClass() {
        initiateClass(savedPath, csvHeader, className, instances);
    }
    
    public static void saveInstances() {
        saveInstances(instances, savedPath, csvHeader);
    }

    public static void getSaved() {
        getSaved(instances, savedPath, className, csvHeader);
    }

    public static String _listInstances() {
        return _listInstances(instances);
    }

    public static void listInstances() {
        listInstances(instances);
    }
    
    public static void newInstance(String[] data) {
        Trip trip = new Trip();
        trip.fromArray(data);
        instances.add(trip);
    }
}
