package behindTheScenes;

import java.util.ArrayList;

import data.Validations;
import functions.stringAndStringToBooleanToString;
import functions.stringToBoolean;

public class Vehicle extends Savable {

    public static ArrayList<Savable> instances = new ArrayList<>();
    public final static String csvHeader = "id,type,capacity,licensePlate";
    public final static String savedPath = "vehicles.csv";
    public final static String className = "Vehicles";
    public final static String[] prompts = { "Type", "Capacity", "License Plate" };
    public final static stringToBoolean[] validators = {e -> Validations.validString(e), e -> Validations.validInt(e), e -> Validations.validName(e)};  

    private String type;
    private int capacity;
    private String licensePlate;

    public Vehicle() {}
    public Vehicle(String type, int capacity, String licensePlate) {
        this.id = generateId();
        this.type = type;
        this.capacity = capacity;
        this.licensePlate = licensePlate;

        Vehicle.instances.add(this);
    }

    public void fromArray(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.type = data[1];
        this.capacity = Integer.parseInt(data[2]);
        this.licensePlate = data[3];
    }

    public String toCsv() {
        return toCsv(this.type + "," + this.capacity + ',' + this.licensePlate);
    }

    public String displayText() {
        return displayText("Type: " + this.type + "\nCapacity: " + this.capacity + "\nLicense Plate: " + this.licensePlate + '\n');
    }
    
    public String toString() {
        return toString(displayText());
    }

    public void editInstance(String keyIndex, stringAndStringToBooleanToString inputFunction) {
        editInstance(keyIndex, inputFunction, prompts, validators);
    }

    public static String editables() {
        return editables(Vehicle.prompts);
    }

    
    public static Savable addInstance(stringAndStringToBooleanToString inputFunction) {
        return addInstance(Vehicle.prompts, Vehicle.instances, Vehicle.validators, Vehicle.className, Vehicle.savedPath, Vehicle.csvHeader, inputFunction);
    }


    public static int generateId() {
        return generateId(instances);
    }

    public static void removeInstance(int id) {
        removeInstance(id, Vehicle.instances, Vehicle.savedPath, Vehicle.csvHeader);
    }

    public static Savable getById(String id) {
        return getById(id, Vehicle.instances);
    }
    
    public static void saveInstances() {
        saveInstances(instances, Vehicle.savedPath, Vehicle.csvHeader);
    }

    public static void getSaved() {
        getSaved(instances, Vehicle.savedPath, Vehicle.className, Vehicle.csvHeader);
    }

    public static String _listInstances() {
        return _listInstances(instances);
    }

    public static void listInstances() {
        listInstances(instances);
    }

    public static void newInstance(String[] data) {
        Vehicle vehicle = new Vehicle();
        vehicle.fromArray(data);
        instances.add(vehicle);
    }

    public static void initiateClass() {
        initiateClass(Vehicle.savedPath, Vehicle.csvHeader, "Vehicles", Vehicle.instances);
    }
}