package actions.Main;

import actions.Action;
import behindTheScenes.Driver;
import behindTheScenes.User;
import flows.DriverFlow;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class StartDriver extends Action {
    public StartDriver() {
        super("Driver", "D");
    }

    public void startFlow(stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        super.startFlow(inputFunction, outputFunction, gui);
        Driver.initiateClass();
        new DriverFlow(new User(), gui);
    }
}
