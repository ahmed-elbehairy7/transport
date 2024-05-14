package behindTheScenes;

import java.util.ArrayList;

import data.Validations;
import functions.stringAndStringToBooleanToString;
import functions.stringToBoolean;

public class Manager extends User {

    public static ArrayList<User> instances = new ArrayList<>();
    public final static String className = "Manager";
    public final static String savedPath = "managers.csv";
    public final static stringToBoolean[] validators = { e -> Validations.validName(e), e -> Validations.validEmail(e), e -> Validations.validUsername(e, Manager.instances), e -> Validations.validPass(e) };

    public Manager() {
    }

    public Manager(int id, String name, String Email, String username, String password) {
        super(id, name, Email, username, password);
        Manager.instances.add(this);
    }
    
    public void writeInstance() {
        writeToFile(Manager.savedPath, toCsv());
    }

    public void editInstance(String keyIndex, stringAndStringToBooleanToString inputFunction) {
        editInstance(keyIndex, prompts, Manager.instances, Manager.validators, Manager.className, Manager.savedPath, Manager.csvHeader,
                inputFunction);
    }
    
    public static String editables() {
        return editables(Manager.prompts);
    }

    public static Savable addInstance(stringAndStringToBooleanToString inputFunction) {
        return addInstance(prompts, Manager.instances, Manager.validators, Manager.className, Manager.savedPath, Manager.csvHeader, inputFunction);
    }


    public static int generateId() {
        return generateId(instances);
    }

    public static void removeInstance(int id) {
        removeInstance(id, Manager.instances, Manager.savedPath, csvHeader);
    }

    public static Savable getById(String id) {
        return getById(id, Manager.instances);
    }
    
    public static void saveInstances() {
        saveInstances(instances, Manager.savedPath, csvHeader);
    }

    public static void getSaved() {
        getSaved(instances, Manager.savedPath, Manager.className, csvHeader);
    }

    public static String _listInstances() {
        return _listInstances(instances);
    }

    public static void listInstances() {
        listInstances(instances);
    }
   
    public static void initiateClass() {
        initiateClass(Manager.savedPath, Manager.className, Manager.instances);
    }

    public static void newInstance(String[] data) {
        Manager manager = new Manager();
        manager.fromArray(data);
        instances.add(manager);
    }
    

}