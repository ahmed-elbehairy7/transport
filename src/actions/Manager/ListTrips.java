package actions.Manager;

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
        Trip trip;
        for (byte i = 0; i < Trip.instances.size(); i++) {
            trip = (Trip) Trip.instances.get(i);
            print(trip.toString(trip.allDetails()));
        }
        return user;
    }
}
