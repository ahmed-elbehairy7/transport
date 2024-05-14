package actions.Manager;

import actions.User.UserAction;
import behindTheScenes.User;
import behindTheScenes.Vehicle;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class AddVehicle extends UserAction  {
    public AddVehicle() {
        super("Add A Vehicle", "V");
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction,
            boolean gui) {
        super.startUserFlow(user, inputFunction, outputFunction, gui);
        Vehicle.addInstance(inputFunc);
        return user;
    }
}
