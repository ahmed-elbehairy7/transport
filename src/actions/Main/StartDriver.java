package actions.Main;

import actions.Action;
import behindTheScenes.User;
import flows.DriverFlow;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class StartDriver extends Action {
    public StartDriver() {
        super("Driver", "D");
    }

    public void startFlow(stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        this.inputFunc = inputFunction;
        this.outputFunc = outputFunction;

        new DriverFlow(new User(), gui);
    }
}
