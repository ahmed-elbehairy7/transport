package actions.Passenger;

import actions.User.UserAction;
import behindTheScenes.Trip;
import behindTheScenes.User;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class ListTrips extends UserAction  {
    public ListTrips() {
        super("List Trips", "L");
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        super.startUserFlow(user, inputFunction, outputFunction, gui);
        print("List of available trips:\n\n" + Trip._listInstances());
        return user;
    }
}
