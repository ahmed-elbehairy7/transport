package actions.Manager;

import actions.User.UserAction;
import behindTheScenes.Trip;
import behindTheScenes.User;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class AddTrip extends UserAction  {
    public AddTrip() {
        super("Add A Trip", "A");
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction,
            boolean gui) {
        super.startUserFlow(user, inputFunction, outputFunction, gui);
        Trip.addInstance(inputFunc);
        return user;
    }
}
