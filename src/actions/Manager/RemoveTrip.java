package actions.Manager;

import actions.User.UserAction;
import behindTheScenes.Trip;
import behindTheScenes.User;
import data.Validations;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class RemoveTrip extends UserAction  {
    public RemoveTrip() {
        super("Remove A Trip", "R");
    }
    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        super.startUserFlow(user, inputFunction, outputFunction, gui);
        print(Trip._listInstances());
        Trip trip = (Trip) Trip.getById(input("Trip id: ", e -> Validations.validInt(e)));
        Trip.removeInstance(trip.id);
        return user;
    }
}
