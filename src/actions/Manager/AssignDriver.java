package actions.Manager;

import actions.User.UserAction;
import behindTheScenes.Trip;
import behindTheScenes.User;
import data.Validations;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class AssignDriver extends UserAction  {
    public AssignDriver() {
        super("Assign A Driver", "D");
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        super.startUserFlow(user, inputFunction, outputFunction, gui);
        new ListTrips().startUserFlow(user, inputFunction, outputFunction, gui);
        String id = input("Trip Id: ", e -> Validations.validInt(e));
        Trip trip = (Trip) Trip.getById(id);
        trip.editInstance("6", inputFunc);
        Trip.saveInstances();
        return user;
    }
}
