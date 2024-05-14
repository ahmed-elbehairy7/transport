package actions.Common;

import actions.User.UserAction;
import behindTheScenes.User;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class Quit extends UserAction {
    public Quit() {
        super("Quit", "Q");
    }

    public void startFlow(stringAndStringToBooleanToString input, stringToVoid output, boolean gui) {
        System.exit(0);
    }

    public User startUserFlow(User user, stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction,
            boolean gui) {
        super.startUserFlow(user, inputFunction, outputFunction, gui);
        System.exit(0);
        return user;
    }
}
