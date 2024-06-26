package actions.Main;

import actions.Action;
import behindTheScenes.Manager;
import behindTheScenes.User;
import flows.ManagerFlow;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class StartManager extends Action {
    public StartManager() {
        super("Manager", "M");
    }

    public void startFlow(stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        super.startFlow(inputFunction, outputFunction, gui);
        Manager.initiateClass();
        new ManagerFlow(new User(), gui);
    }
}
