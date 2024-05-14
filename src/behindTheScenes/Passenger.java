package behindTheScenes;

import java.util.ArrayList;

import data.Validations;
import functions.stringAndStringToBooleanToString;
import functions.stringToBoolean;

public class Passenger extends User {

    public static ArrayList<User> instances = new ArrayList<>();
    public final static String savedPath = "passengers.csv";
    public final static String className = "Passenger";
    public final static stringToBoolean[] validators = { e -> Validations.validName(e), e -> Validations.validEmail(e),e -> Validations.validUsername(e, Passenger.instances), e -> Validations.validPass(e) };

    public Passenger() {
    }

    public Passenger(int id, String name, String Email, String username, String password) {
        super(id, name, Email, username, password);
        Passenger.instances.add(this);
    }

    public void writeInstance() {
        writeToFile(Passenger.savedPath, toCsv());
    }

    public void editInstance(String keyIndex, stringAndStringToBooleanToString inputFunction) {
        editInstance(keyIndex, prompts, Passenger.instances, Passenger.validators, Passenger.className,
                Passenger.savedPath, Passenger.csvHeader, inputFunction);
    }
    
    public static String editables() {
        return editables(Passenger.prompts);
    }

    public static Savable addInstance(stringAndStringToBooleanToString inputFunction) {
        return addInstance(prompts, Passenger.instances, Passenger.validators, Passenger.className, Passenger.savedPath, Passenger.csvHeader, inputFunction);
    }

    public static int generateId() {
        return generateId(instances);
    }

    public static void removeInstance(int id) {
        removeInstance(id, Passenger.instances, Passenger.savedPath, csvHeader);
    }

    public static Savable getById(String id) {
        return getById(id, Passenger.instances);
    }
    
    public static void saveInstances() {
        saveInstances(instances, Passenger.savedPath, csvHeader);
    }

    public static void getSaved() {
        getSaved(instances, Passenger.savedPath, Passenger.className, csvHeader);
    }

    public static String _listInstances() {
        return _listInstances(instances);
    }

    public static void listInstances() {
        listInstances(instances);
    }
   
    public static void initiateClass() {
        initiateClass(Passenger.savedPath, Passenger.className, Passenger.instances);
    }

    public static void newInstance(String[] data) {
        Passenger passenger = new Passenger();
        passenger.fromArray(data);
        instances.add(passenger);
    }
}
