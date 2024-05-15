package behindTheScenes;

import java.util.ArrayList;

import functions.stringAndStringToBooleanToString;
import functions.stringToBoolean;


public class User extends Savable{
    public static String csvHeader = "id,Name,Email,username,password";
    public final static String[] prompts = {"Name", "Email", "Username", "Password"};

    public String name;
    public String username;
    public String password;
    public String Email;
    public boolean success = false;

    public User() {}

    public User(int id, String name, String Email, String username, String password) {
        this.id = id;

        this.name = name;
        this.Email = Email;
        this.username = username;
        this.password = password;

    }

    public User(String name, String username, String password, String Email, ArrayList<User> instances) {
        this.id = generateId(instances);

        this.name = name;
        this.Email = Email;
        this.username = username;
        this.password = password;

    }

    public void fromArray(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.Email = data[2];
        this.username = data[3];
        this.password = data[4];
    }

    public String toCsv() {
        return toCsv(this.name + "," + this.Email + "," + this.username + "," + this.password);
    }

    public String displayText() {
        return displayText("Name: " + this.name + "\nEmail: "+ this.Email + "\nUsername: " + this.username + "\nPassword: " + this.password + "\n");
    }
    
    public String toString() {
        return toString(displayText());
    }
    
    public static User login(String username, String password, ArrayList<User> instances) {

        User user;
        for (short i = 0; i < instances.size(); i++) {
            user = ((User) instances.get(i));
            if (!(user.username.equals(username))) {
                continue;
            }
            if (!(user.password.equals(password))) {
                continue;
            }
            user.success = true;
            return user;
        }
        return new User();

    }

    public void editInstance(String keyIndex, ArrayList<?> instances, stringToBoolean[] validators, stringAndStringToBooleanToString inputFunction) {
        editInstance(keyIndex, instances, validators, inputFunction);
    }
        
    public static Savable addInstance(ArrayList<?> instances, stringToBoolean[] validators, String className, String savedPath, stringAndStringToBooleanToString inputFunction) {
        return addInstance(prompts, instances, validators, className, savedPath, csvHeader, inputFunction);
    }

    public static void removeInstance(int id, ArrayList<User> instances, String savedPath) {
        removeInstance(id, instances, savedPath, csvHeader);
    }

    public static String editables() {
        return editables(prompts);
    }

    
    public static void saveInstances(ArrayList<User> instances, String savedPath) {
        saveInstances(instances, savedPath, csvHeader);
    }

    public static void getSaved(ArrayList<User> instances, String savedPath, String className) {
        getSaved(instances, savedPath, className, csvHeader);
    }
   
    public static void initiateClass(String usersFile, String className, ArrayList<?> instances) {
        initiateClass(usersFile, csvHeader, className, instances);
    }
}