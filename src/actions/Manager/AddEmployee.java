package actions.Manager;

import actions.User.UserAction;
import behindTheScenes.Driver;
import behindTheScenes.User;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class AddEmployee extends UserAction  {
    public AddEmployee() {
        super("Add An Employee (Driver)", "M");
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction,
            boolean gui) {
        super.startUserFlow(user, inputFunction, outputFunction, gui);
        Driver.addInstance(inputFunc);
        return user;
    }
}
