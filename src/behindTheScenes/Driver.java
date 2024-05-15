package behindTheScenes;
import java.util.ArrayList;

import data.Validations;
import functions.stringAndStringToBooleanToString;
import functions.stringToBoolean;

public class Driver extends User {

    public static ArrayList<User> instances = new ArrayList<>();
    public final static String className = "Driver";
    public final static String savedPath = "drivers.csv";
    public final static stringToBoolean[] validators = { e -> Validations.validName(e), e -> Validations.validEmail(e), e -> Validations.validUsername(e, Driver.instances), e -> Validations.validPass(e) };

    public Driver() {}
    public Driver( String name, String username, String password, String Email) {
        super(generateId(), name, username, password, Email);
        Driver.instances.add(this);

    }

    public Driver(int id, String name, String Email, String username, String password) {
        super(id, name, Email, username, password);
        Driver.instances.add(this);
    }

    public static String _listDrivers() {
        Driver driver;
        String text = "";
        for (short i = 0; i < Driver.instances.size(); i++) {
            driver = (Driver) Driver.instances.get(i);
            text += "\n\nId: " + driver.id + "\nName: " + driver.name + "\n";
        }
        return text;
    }

    public void writeInstance() {
        writeInstance(Driver.savedPath);
    }

    public void editInstance(String keyIndex, stringAndStringToBooleanToString inputFunction) {
        editInstance(keyIndex, instances, validators, inputFunction);
    }

    public static String editables() {
        return editables(Driver.prompts);
    }
    
    public static Savable addInstance(stringAndStringToBooleanToString inputFunction) {
        return addInstance(prompts, instances, Driver.validators, Driver.className, Driver.savedPath, Driver.csvHeader, inputFunction);
    }


    public static int generateId() {
        return generateId(instances);
    }

    public static void removeInstance(int id) {
        removeInstance(id, instances, Driver.savedPath, csvHeader);
    }

    public static Savable getById(String id) {
        return getById(id, instances);
    }

    public static void initiateClass() {
        initiateClass(Driver.savedPath, csvHeader, Driver.className, instances);
    }
    
    public static void saveInstances() {
        saveInstances(instances, Driver.savedPath, csvHeader);
    }

    public static void getSaved() {
        getSaved(instances, Driver.savedPath, Driver.className, csvHeader);
    }

    public static String _listInstances() {
        return _listInstances(instances);
    }

    public static void listInstances() {
        listInstances(instances);
    }


    public static void listDrivers() {
        System.out.println(_listDrivers());
    }

    public static void newInstance(String[] data) {
        Driver driver = new Driver();
        driver.fromArray(data);
        instances.add(driver);
    }
    
}