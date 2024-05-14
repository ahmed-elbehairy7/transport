package actions.Driver;

import actions.User.UserAction;
import behindTheScenes.Trip;
import behindTheScenes.User;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class ListAssignedTrips extends UserAction {
    public ListAssignedTrips() {
        super("List Assigned Trips", "L");
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        super.startUserFlow(user, inputFunction, outputFunction, gui);
        Trip trip;
        for (byte i = 0; i < Trip.instances.size(); i++) {
            trip = (Trip) Trip.instances.get(i);
            if (trip.driverId == user.id) {
                print(trip.toString());
            }
        }
        return user;
    }
}
