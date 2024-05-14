package behindTheScenes;

import java.util.ArrayList;

import data.Validations;
import functions.stringAndStringToBooleanToString;
import functions.stringToBoolean;

public class Ticket extends Savable {

    public static ArrayList<Savable> instances = new ArrayList<>();
    public final static String csvHeader = "id,passengerId,tripId";
    public final static String savedPath = "tickets.csv";
    public final static String className = "Ticket";
    public final static String[] prompts = {"Trip id"};
    public final static stringToBoolean[] validators = {e -> Validations.validInt(e)}; 

    public int passengerId;
    public int tripId;
    
    public Ticket() {
    }

    public Ticket(int passengerId, int tripId) {
        this.id = generateId(Ticket.instances);
        this.passengerId = passengerId;
        this.tripId = tripId;

        Ticket.instances.add( this);
    }

    public Ticket(int id, int passengerId, int tripId) {
        this.id = id;
        this.passengerId = passengerId;
        this.tripId = tripId;

        Ticket.instances.add(this);
    }

    public void fromArray(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.passengerId = Integer.parseInt(data[1]);
        this.tripId = Integer.parseInt(data[2]);
    }
    
    public String toCsv() {
        return toCsv(this.passengerId + "," + tripId);
    }

    public String displayText() {
        return displayText("Passenger ID: " + this.passengerId + "\nTrip ID: " + this.tripId + "\n");
    }


    public String toString() {
        Trip trip;

        for (short i = 0; i < Trip.instances.size(); i++) {
            trip = (Trip) Trip.instances.get(i);
            if (trip.id == this.tripId) {
                return toString(displayText() + "\nTrip Info:\n---\n" + trip.displayText());
            }
        }
        return "";
    }

    public void writeInstance() {
        writeInstance(savedPath);
    }

    public void editInstance(String keyIndex, stringAndStringToBooleanToString inputFunction) {
        editInstance(keyIndex, prompts, instances, validators, className, savedPath, csvHeader, inputFunction);
    }

    public static String editables() {
        return editables(Ticket.prompts);
    }
    
    public static Savable addInstance(stringAndStringToBooleanToString inputFunction) {
        return addInstance(prompts, instances, validators, className, savedPath, Ticket.csvHeader, inputFunction);
    }


    public static int generateId() {
        return generateId(instances);
    }

    public static Savable getById(String id) {
        return getById(id, instances);
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

    public static String _listTickets(int passengerId) {
        String text = "";
        Ticket ticket;
        for (short i = 0; i < instances.size(); i++) {
            ticket = (Ticket) instances.get(i);
            if (ticket.passengerId == passengerId) {
                text += ticket.toString() + "\n";
            }
        }

        return text;
    }
    
    public static void newInstance(String[] data) {
        Ticket ticket = new Ticket();
        ticket.fromArray(data);
        instances.add(ticket);
    }

    public static void removeInstance(int id) {

        for (short i = 0; i < instances.size(); i++) {
            Ticket ticket = (Ticket) instances.get(i);
            if (ticket.id == id) {
                instances.remove(i);
                saveInstances(Ticket.instances, Ticket.savedPath, Ticket.csvHeader);
                Trip trip = (Trip) Trip.getById(ticket.tripId + "", Trip.instances);
                trip.seats++;
                saveInstances(Trip.instances, Trip.savedPath, Trip.csvHeader);

            }
        }

    }

    public static void initiateClass() {
        initiateClass(Ticket.savedPath, Ticket.csvHeader, Ticket.className, Ticket.instances);
    }    
    
}
