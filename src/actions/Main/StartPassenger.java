package actions.Main;

import actions.Action;
import behindTheScenes.Passenger;
import behindTheScenes.User;
import flows.PassengerFlow;
import functions.stringAndStringToBooleanToString;
import functions.stringToVoid;

public class StartPassenger extends Action {
    public StartPassenger() {
        super("Passenger", "P");
    }

    public void startFlow(stringAndStringToBooleanToString inputFunction, stringToVoid outputFunction, boolean gui) {
        super.startFlow(inputFunction, outputFunction, gui);
        Passenger.initiateClass();
        new PassengerFlow(new User(), gui);
    }
}
