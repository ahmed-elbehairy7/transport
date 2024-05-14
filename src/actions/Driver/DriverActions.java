package actions.Driver;

import java.util.ArrayList;

import actions.User.UserAction;
import behindTheScenes.User;

public class DriverActions {
    public static ListAssignedTrips listAssignedTrips = new ListAssignedTrips();
    
    public static ArrayList<UserAction> actions(User usr) {
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add((UserAction) listAssignedTrips);
        return actions;
    }
}
