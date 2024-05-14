package actions.Manager;

import java.util.ArrayList;

import actions.User.UserAction;

public class ManagerActions {
    public static ListTrips listTrips = new ListTrips();
    public static AddTrip addTrip = new AddTrip();
    public static RemoveTrip removeTrip = new RemoveTrip();
    public static EditTrip editTrip = new EditTrip();
    public static AssignDriver assignDriver = new AssignDriver();
    public static AddVehicle addVehicle = new AddVehicle();
    public static AddEmployee addEmployee = new AddEmployee();


    public static ArrayList<UserAction> actions() {
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add((UserAction) listTrips); 
        actions.add((UserAction) addTrip); 
        actions.add((UserAction) assignDriver); 
        actions.add((UserAction) editTrip); 
        actions.add((UserAction) removeTrip); 
        actions.add((UserAction) addVehicle); 
        actions.add((UserAction) addEmployee); 
        return actions;
    }
}
